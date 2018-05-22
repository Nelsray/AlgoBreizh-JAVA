/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algobreizh.Views;

import algobreizh.Models.Meeting;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author quentinmartinez
 */
public class MeetingsTable extends JTable{
    
    public  MeetingsTable(List<Meeting> meetings)
    { 
          
            this.setModel(new TableModelMeeting(meetings));
            this.setRowHeight(30);
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment( JLabel.CENTER );
            this.setDefaultRenderer(String.class, centerRenderer);
          
    } 
    
     public class TableModelMeeting  extends AbstractTableModel implements DefaultTableModel{
            private static final long serialVersionUID = 1L;
            private List<Meeting> meetings = new ArrayList<Meeting>();
	    private String[] columnNames = {"CLIENT","CONTACT","TELEPHONE", "DATE", "INFORMATIONS" };

	    public TableModelMeeting(List<Meeting> _meetings){
	         this.meetings = _meetings;
	      
	    }
	    @Override
	    public Meeting getItem(int index){
	         return meetings.get(index);
	    }
            
	    public String getColumnName(int columnIndex){
	         return columnNames[columnIndex];
	    }
	    
            public int getRowCount() {
	       return this.meetings.size();
	    }  
	    
            public int getColumnCount() {
	       return this.columnNames.length;
	    }
            
	    public Object getValueAt(int rowIndex, int columnIndex) {
	    	Meeting meeting = meetings.get(rowIndex);
	        switch (columnIndex) {
	            case 0:
	                return meeting.getCustomer().getFirstname() + " " + meeting.getCustomer().getLastname();
                    case 1:
	                return meeting.getContact();
                    case 2:
	                return meeting.getTelephone();
	            case 3: 
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                        return meeting.getDateTime().format(formatter);
	            case 4:
	                return meeting.getInfos();	        
	           }
	           return null;
	   }
	   
            public Class<?> getColumnClass(int columnIndex){   
	               return String.class;
	      }
            
           @Override
            public void addRow(Object row){
		meetings.add((Meeting)row);
	        fireTableDataChanged();
            }
            
            @Override
            public void removeRow(int row){
		meetings.remove(row);
	        fireTableDataChanged();
	    }
           
            public boolean isCellEditable(int row, int col){
		      return true;
            }
}
}
