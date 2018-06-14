/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algobreizh.dao;

import algobreizh.Models.Salesman;
import algobreizh.sql.dao.DAO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalesmanDAO extends DAO<Salesman> {

    public SalesmanDAO(Connection conn) {
        super(conn);
    }

    @Override
    public boolean create(Salesman obj) {
        String query = "INSERT INTO tCustomers VALUES ("
                + "\'" + obj.getFirstname()
                + "\',\'" + obj.getLastname()
                + "\')";
        this.execute(query);
        return true;
    }

    public Salesman getByCredentials(String username, String password) {
        String query = "SELECT * FROM tSalesman "
                + "WHERE username = '" + username + "' "
                + "AND password = '" + password + "' ";
        Salesman salesman = null;
        ResultSet res = this.execute(query);
        if (res != null) {
            try {
                while (res.next()) {
                    String lastname = res.getString("lastname");
                    String firstname = res.getString("firstname");
                    int id = res.getInt("id");
                    salesman = new Salesman(id, firstname, lastname);
                }
            } catch (SQLException e) {
                return null;
            }
        }
        return salesman;
    }

    @Override
    public boolean update(Salesman obj) {
        String query = "UPDATE FROM tSalesman SET (firstname = "
                + "\'" + obj.getFirstname()
                + "\',lastname =\'" + obj.getLastname()
                + "\')";
        ResultSet res = this.execute(query);
        return true;
    }

    @Override
    public Salesman get(int id) {
        String querry = "SELECT * FROM tSalesman WHERE id = " + id;
        Salesman salesman = null;
        ResultSet res = this.execute(querry);
        if (res != null) {
            try {
                while (res.next()) {
                    String lastname = res.getString("lastname");
                    String firstname = res.getString("firstname");
                    salesman = new Salesman(id, firstname, lastname);
                }
            } catch (SQLException e) {
                return null;
            }
        }
        return salesman;
    }

    @Override
    public List<Salesman> getAll() {
        String querry = "SELECT * FROM tSalesman";
        List<Salesman> salesmans = new ArrayList();
        ResultSet res = this.execute(querry);
        if (res != null) {
            try {
                while (res.next()) {
                    int id = res.getInt("id");
                    String lastname = res.getString("lastname");
                    String firstname = res.getString("firstname");
                    salesmans.add(new Salesman(id, firstname, lastname));
                }
            } catch (SQLException e) {
                return null;
            }
        }
        return salesmans;
    }

    @Override
    public boolean delete(Salesman obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
