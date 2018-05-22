/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algobreizh.sql.dao.factory;

import algobreizh.Database.DatabaseConnection;
import algobreizh.Models.City;
import algobreizh.Models.Customer;
import algobreizh.Models.Meeting;
import algobreizh.Models.Salesman;
import algobreizh.dao.CitiesDAO;
import algobreizh.dao.CustomerDAO;
import algobreizh.dao.MeetingsDAO;
import algobreizh.dao.SalesmanDAO;
import algobreizh.sql.dao.DAO;
import java.sql.Connection;

/**
 *
 * @author quentinmartinez
 */
public class DAOFactory  extends AbstractDAOFactory{
	
    protected static final Connection conn = DatabaseConnection.getInstance();   

    @Override
    public DAO<City> getCitiesDAO() {
      return new CitiesDAO(conn);
    }

    @Override
    public DAO<Customer> getCustomerDAO() {
        return new CustomerDAO(conn); 
    }

    @Override
    public DAO<Meeting> getMeetingsDAO() {
        return new MeetingsDAO(conn);
    };

    @Override
    public DAO<Salesman> getSalesmanDAO() {
        return new SalesmanDAO(conn);
    }


}

