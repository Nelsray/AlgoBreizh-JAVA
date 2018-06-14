/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algobreizh.dao;

import algobreizh.sql.dao.DAO;
import algobreizh.Models.City;
import algobreizh.Models.Meeting;
import algobreizh.Models.Salesman;
import algobreizh.sql.dao.factory.AbstractDAOFactory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CitiesDAO extends DAO<City> {

    public CitiesDAO(Connection conn) {
        super(conn);
    }

    @Override
    public boolean create(City obj) {
        String querry = "INSERT INTO tCities(id, name, id_tCustomer) VALUES (\'"
                + "\',\'" + obj.getId()
                + "\',\'" + obj.getName()
                + "\',\'" + obj.getSalesman().getId()
                + "\')";
        this.execute(querry);
        return true;
    }

    @Override
    public boolean delete(City obj) {
        String querry = "DELETE * FROM tCities WHERE id = " + obj.getId();
        this.execute(querry);
        return true;
    }

    @Override
    public boolean update(City obj) {
        String querry = "UPDATE * FROM tCities WHERE id = " + obj.getId();
        this.execute(querry);
        return true;
    }

    @Override
    public City get(int id) {
        String query = "SELECT * FROM tCities WHERE id = " + id;
        City city = null;
        try {

            AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
            DAO<Salesman> salesmanDAO = adf.getSalesmanDAO();
            ResultSet res = this.execute(query);
            if (res != null) {
                try {
                    while (res.next()) {
                        String name = res.getString("name");
                        int id_tSalesman = res.getInt("id_tSalesman");
                        city = new City(id, name, salesmanDAO.get(id_tSalesman));
                    }
                } catch (SQLException e) {
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return city;
    }

    @Override
    public List<City> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
