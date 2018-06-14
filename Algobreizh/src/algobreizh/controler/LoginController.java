/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algobreizh.controler;

import algobreizh.Database.DatabaseConnection;
import algobreizh.Models.Salesman;
import algobreizh.Views.LoginView;
import algobreizh.Views.WelcomeView;
import algobreizh.context.Context;
import algobreizh.dao.SalesmanDAO;
import algobreizh.sql.dao.DAO;
import algobreizh.sql.dao.factory.AbstractDAOFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {

    private LoginView m_view;

    public LoginController(LoginView loginView) {
        m_view = loginView;
        m_view.addBtnConnectListener(new BtnConnectionListener());
        m_view.addBtnQuitListener(new BtnQuitListener());

    }

    public void setVisible(Boolean visible) {
        m_view.setVisible(visible);
    }

    class BtnConnectionListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            SalesmanDAO salesmanDAO
                    = new SalesmanDAO(DatabaseConnection.getInstance());
            Context.currUser = salesmanDAO.getByCredentials(
                    m_view.getUsername(), m_view.getPassword());
            if (Context.currUser != null) {
                m_view.setVisible(false);
                WelcomeView meetingsView = new WelcomeView();
                WelcomeControler welcomeControler = new WelcomeControler(meetingsView);
                welcomeControler.setVisible(true);
            }
        }
    }

    class BtnQuitListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            Runtime.getRuntime().exit(1);
        }
    }
}
