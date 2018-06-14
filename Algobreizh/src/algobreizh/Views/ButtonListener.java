/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algobreizh.Views;

import algobreizh.Models.Customer;
import algobreizh.controler.LoginController;
import algobreizh.controler.NewMeetingController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTable;

public class ButtonListener implements ActionListener {

    private int row;
    private JTable table;
    private JButton button;

    public void setColumn(int col) {
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public JButton getButton() {
        return this.button;
    }

    public void actionPerformed(ActionEvent event) {
        Customer customer = (Customer) ((DefaultTableModel) table.getModel()).getItem(this.row);
        NewMeetingView newMeetingView = new NewMeetingView(customer);
        NewMeetingController newMeetingControler = new NewMeetingController(newMeetingView, customer);
        newMeetingControler.setVisible(true);
    }
}
