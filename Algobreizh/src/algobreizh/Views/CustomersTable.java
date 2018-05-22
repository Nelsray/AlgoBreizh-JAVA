/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algobreizh.Views;


import algobreizh.Models.Customer;
import algobreizh.Models.Meeting;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

public class CustomersTable extends JTable{
    
    public  CustomersTable(List<Customer> customers)
    { 
            this.setModel(new CustomersTable.TableModelCustomer(customers));
            this.setRowHeight(30);
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment( JLabel.CENTER );
            this.setDefaultRenderer(String.class, centerRenderer);  
            this.getColumn("RENDEZ-VOUS").setCellRenderer(new ButtonRenderer("NOUVEAU"));
            this.getColumn("RENDEZ-VOUS").setCellEditor(new ButtonEditor(new JCheckBox("NOUVEAU"), new ButtonListener()));
    } 
    
    public class TableModelCustomer extends AbstractTableModel implements DefaultTableModel{
         
            private List<Customer> customers;
            
            private String[] columnNames = {"NOM","E-MAIL", "VILLE", "RENCONTRE", "RENDEZ-VOUS" };

	    public TableModelCustomer(List<Customer> _customers){
	       this.customers = _customers;
	    }
            
	    @Override
	    public Customer getItem(int index){
                return customers.get(index);
	    }
            
	    public String getColumnName(int columnIndex){
                return columnNames[columnIndex];
	    }
	    
            public int getRowCount() {
                return customers.size();
	    }  
	    
            public int getColumnCount() {
                return columnNames.length;
	    }
            
	    public Object getValueAt(int rowIndex, int columnIndex) {
	        Customer customer = customers.get(rowIndex);
	        switch (columnIndex) {
	            case 0:
	                return customer.getFirstname() + " " + customer.getLastname();
                    case 1:
	                return customer.getEmail();
                    case 2:
	                return customer.getCity().getName();
	            case 3:
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");
                        return customer.getLastDate().format(formatter);
                    case 4:
                        return "NOUVEAU";
	           }
	           return null;
            }
	   
            public Class<?> getColumnClass(int columnIndex){   
	               return String.class;
	    }
            
           @Override
            public void addRow(Object row){
		
	        fireTableDataChanged();
            }
            
            @Override
            public void removeRow(int row){
		
	        fireTableDataChanged();
	    }
           
            public boolean isCellEditable(int row, int col){
		      return true;
            }
}
}
