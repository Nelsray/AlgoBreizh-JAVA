/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algobreizh.dao;

import algobreizh.Database.DatabaseConnection;
import algobreizh.sql.dao.DAO;
import algobreizh.Models.City;
import algobreizh.Models.Customer;
import algobreizh.Models.Salesman;
import algobreizh.sql.dao.factory.AbstractDAOFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerDAO extends DAO<Customer> {

    AbstractDAOFactory adf = null;

    public CustomerDAO(Connection conn) {
        super(conn);
        adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
    }

    @Override
    public boolean create(Customer c) {
        try {
            String query = "INSERT INTO tCustomers VALUES ("
                    + "\'" + c.getFirstname()
                    + "\',\'" + c.getLastname()
                    + "\',\'" + c.getEmail()
                    + "\',\'" + c.getCity().getId()
                    + "\')";
            this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
                    .executeQuery(query);
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Customer obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Customer obj) {
        try {
            Timestamp timestamp = Timestamp.valueOf(obj.getLastDate());
            String querry = "UPDATE tCustomers SET lastMeetingDate = ? WHERE id = ?";
            PreparedStatement ps = DatabaseConnection.getInstance().prepareStatement(querry);
            ps.setTimestamp(1, timestamp);
            ps.setInt(2, obj.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    @Override
    public Customer get(int id) {
        Customer customer = null;
        try {
            ResultSet res = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
                    .executeQuery("SELECT * FROM tCustomers WHERE id = " + id);
            if (res != null) {
                while (res.next()) {
                    String lastname = res.getString("lastname");
                    String firstname = res.getString("firstname");
                    String email = res.getString("email");
                    Timestamp lastDate = res.getTimestamp("lastMeetingDate");
                    int id_tCities = res.getInt("id_tCities");
                    customer = new Customer(id, firstname, lastname, email, new City(id_tCities), lastDate.toLocalDateTime());
                }
            }
        } catch (SQLException e) {

        }
        return customer;
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> customers = new ArrayList<Customer>();
        try {
            DAO<City> cityDAO = adf.getCitiesDAO();
            ResultSet res = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
                    .executeQuery("SELECT * FROM tCustomers ORDER BY lastMeetingDate ASC");
            if (res != null) {
                while (res.next()) {
                    String lastname = res.getString("lastname");
                    int id = res.getInt("id");
                    String firstname = res.getString("firstname");
                    String email = res.getString("email");
                    Timestamp lastDate = res.getTimestamp("lastMeetingDate");
                    int id_tCities = res.getInt("id_tCities");

                    customers.add(new Customer(id, firstname, lastname, email, cityDAO.get(id_tCities), lastDate.toLocalDateTime()));
                }
            }
        } catch (SQLException e) {

        }
        return customers;
    }
}
