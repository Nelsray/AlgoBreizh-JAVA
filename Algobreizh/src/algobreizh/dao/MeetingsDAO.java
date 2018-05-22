/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algobreizh.dao;

import algobreizh.Database.DatabaseConnection;
import algobreizh.Models.Customer;
import algobreizh.Models.Meeting;
import algobreizh.Models.Salesman;
import algobreizh.sql.dao.DAO;
import algobreizh.sql.dao.factory.AbstractDAOFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author quentinmartinez
 */
public class MeetingsDAO  extends DAO<Meeting>{
    public MeetingsDAO(Connection conn)
    {
        super(conn);
    }

    @Override
    public boolean create(Meeting obj) {
       try {
            Timestamp timestamp = Timestamp.valueOf(obj.getDateTime());
            String querry = "INSERT INTO tMeetings(description, contact, telephone, meetingDate, id_tSalesman, id_tCustomers) VALUES (?,?,?,?,?,?)"; 
            PreparedStatement ps = DatabaseConnection.getInstance().prepareStatement(querry);
            ps.setString(1, obj.getInfos());
            ps.setString(2, obj.getContact());
            ps.setString(3, obj.getTelephone());
            ps.setTimestamp(4, timestamp);
            ps.setInt(5, obj.getSalesman().getId());
            ps.setInt(6, obj.getCustomer().getId());
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(MeetingsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    @Override
    public boolean delete(Meeting obj) {
        String query = "DELETE FROM tMeetings WHERE id = " + obj.getId();
        this.execute(query);
        return true;
    }

    @Override
    public boolean update(Meeting obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Meeting> getAll() {
        AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
        
        
        DAO<Salesman> salesmanDAO = adf.getSalesmanDAO();
        DAO<Customer> customersDAO = adf.getCustomerDAO();
        
        List<Meeting> meetings = new ArrayList<>();
        
        String query = "SELECT * FROM tMeetings";
        ResultSet res = this.execute(query);
        if (res != null) {
            try {
		while (res.next()) {
                    
                    int id = res.getInt("id");
                    Timestamp timestamp = res.getTimestamp("MeetingDate");
                    //Date date = res.getDate("MeetingDate");
                    Customer customer = customersDAO.get(res.getInt("id_tCustomers"));
                    Salesman salesman = salesmanDAO.get(res.getInt("id_tSalesman"));
                    String desc = res.getString("description");
                    String contact = res.getString("contact");
                    String telephone = res.getString("telephone");
                    Meeting m = new Meeting(id, salesman,customer, timestamp.toLocalDateTime(), desc,contact,telephone);
                    meetings.add(m);
		}
            } catch (SQLException e) {
                System.out.println("Algobreizh SQL Exception: " + e);
            }
        }
        return meetings;   
    }

    @Override
    public Meeting get(int id) {
      AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
        DAO<Salesman> salesmanDAO = adf.getSalesmanDAO();
        DAO<Customer> customersDAO = adf.getCustomerDAO();
        
        List<Meeting> meetings = new ArrayList<>();
        
        String query = "SELECT * FROM tMeetings WHERE id" + id;
        ResultSet res = this.execute(query);
        if (res != null) {
            try {
		while (res.next()) {

                     Timestamp timestamp = res.getTimestamp("MeetingDate");
                    Customer customer = customersDAO.get(res.getInt("id_tCustomer"));
                    Salesman salesman = salesmanDAO.get(res.getInt("id_tSalesman"));
                    String desc = res.getString("desc");
                    String contact = res.getString("contact");
                    String telephone = res.getString("telephone");
                    return new Meeting(id, salesman,customer, timestamp.toLocalDateTime(), desc,contact,telephone);
                    
		}
            } catch (SQLException e) {
                System.out.println("Algobreizh SQL Exception: " + e);
            }
        }
        return null;       
    }

   
    
}
