/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algobreizh.sql.dao.factory;

import algobreizh.Models.Customer;
import algobreizh.sql.dao.DAO;

public abstract class AbstractDAOFactory {

    public static final int DAO_FACTORY = 0;

    public abstract DAO getCitiesDAO();

    public abstract DAO<Customer> getCustomerDAO();

    public abstract DAO getMeetingsDAO();

    public abstract DAO getSalesmanDAO();

    public static AbstractDAOFactory getFactory(int type) {
        switch (type) {
            case DAO_FACTORY:
                return new DAOFactory();
            default:
                return null;
        }
    }
}
