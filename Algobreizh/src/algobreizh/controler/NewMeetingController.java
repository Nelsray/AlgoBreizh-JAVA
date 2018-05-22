/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algobreizh.controler;

import algobreizh.Models.Customer;
import algobreizh.Models.Meeting;
import algobreizh.Views.CustomersTable;
import algobreizh.Views.NewMeetingView;
import algobreizh.context.Context;
import algobreizh.sql.dao.DAO;
import algobreizh.sql.dao.factory.AbstractDAOFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import java.util.Date;
import java.util.GregorianCalendar;

public class NewMeetingController {
    private NewMeetingView m_view;
    private Customer customer;
    
    public void setVisible(Boolean visible)  {     m_view.setVisible(visible);      }
        
    public NewMeetingController(NewMeetingView newMeetingView, Customer customer){
        this.m_view = newMeetingView;
        this.customer = customer;
        m_view.addBtnSaveListener(new BtnSaveListener());
        m_view.addBtnCancelListener(new BtnCancelListener());
        
    }
    
    
    class BtnSaveListener implements ActionListener {
	        public void actionPerformed(ActionEvent e) {
                    
                    if (!m_view.isFilled())
                    {
                       return; 
                    }
                    m_view.getContentPane().removeAll();
                    AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
                    DAO<Meeting> meetingsDAO = adf.getMeetingsDAO();
                    DAO<Customer> customersDAO = adf.getCustomerDAO();
                    Meeting meeting = new Meeting(0, Context.currUser, customer, m_view.getMeetingDate(), 
                    m_view.getInformations(), m_view.getContactName(),m_view.getTelephone());
                    meetingsDAO.create(meeting);
                    customer.setLastDate(m_view.getMeetingDate());
                    customersDAO.update(customer);
                    setVisible(false); 
	        }
	 }
    
    class BtnCancelListener implements ActionListener {
	        public void actionPerformed(ActionEvent e) {
	        	 setVisible(false); 
	        }
	 }
    
    static public LocalDateTime toLdt(Date date) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        ZonedDateTime zdt = cal.toZonedDateTime();
        return zdt.toLocalDateTime();
}
}
