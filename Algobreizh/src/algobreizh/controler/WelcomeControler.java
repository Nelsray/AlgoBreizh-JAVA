/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algobreizh.controler;

import algobreizh.Models.Customer;
import algobreizh.Models.Meeting;
import algobreizh.Views.CustomersTable;
import algobreizh.Views.MeetingsTable;
import algobreizh.Views.WelcomeView;
import algobreizh.context.Context;
import algobreizh.sql.dao.DAO;
import algobreizh.sql.dao.factory.AbstractDAOFactory;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JScrollPane;

public class WelcomeControler {
    
	private WelcomeView  m_view;
        
	public WelcomeControler (WelcomeView welcomeView)
	{
            m_view = welcomeView; 
            m_view.addMenuCustomersViewListener(new BtnCustomersListener());
            m_view.addMenuMeetingsViewListener(new BtnMeetingsListener());
            new BtnMeetingsListener().actionPerformed(null);
	}
        
        public void setVisible(Boolean visible)  {     m_view.setVisible(visible);      }
      
        private List<Meeting> FilterMeetings(List<Meeting> meetings)
        {
            return  meetings.stream().filter(m -> m.getSalesman().getId() == Context.currUser.getId()).collect(Collectors.toList());         
        }
        
	private List<Customer> FilterCustomers(List<Customer> customers)
        {
            return customers.stream().filter(c -> c.getCity().getSalesman().getId() == Context.currUser.getId()).collect(Collectors.toList());
        }
        
	class BtnCustomersListener implements ActionListener {
	        public void actionPerformed(ActionEvent e) {
                    m_view.getContentPane().removeAll();
                    AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
                    DAO<Customer> customersDAO = adf.getCustomerDAO();
                    List<Customer> customers = customersDAO.getAll();
                    customers = FilterCustomers(customers);
                    
                    m_view.getContentPane().add(new JScrollPane(new CustomersTable(customers)),BorderLayout.CENTER);   
                    m_view.getContentPane().revalidate();
	        }
	 }
        
	 class BtnMeetingsListener implements ActionListener {
	        public void actionPerformed(ActionEvent e) {
                    m_view.getContentPane().removeAll();
                    AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
                    DAO<Meeting> meetingsDAO = adf.getMeetingsDAO();
                    List<Meeting> meetings = meetingsDAO.getAll();
                    meetings = FilterMeetings(meetings);
                    
                    m_view.getContentPane().add(new JScrollPane(new MeetingsTable(meetings)),BorderLayout.CENTER);   
                    m_view.getContentPane().revalidate();
                }
	 }
         
      
		
}
