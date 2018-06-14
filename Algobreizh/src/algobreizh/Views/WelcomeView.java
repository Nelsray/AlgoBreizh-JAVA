/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algobreizh.Views;

import algobreizh.context.Context;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;

public class WelcomeView extends JFrame {

    private JTable table;
    private JMenuBar bar = new JMenuBar();
    private JMenu menuCustomers = new JMenu("Clients");
    private JMenuItem menuCustomersView = new JMenuItem("Voir");
    private JMenu menuMeetings = new JMenu("Rendez-vous");
    private JMenuItem menuMeetingsView = new JMenuItem("Voir");

    public WelcomeView() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Gestion des rendez-vous ("
                + Context.currUser.getFirstname() + " " + Context.currUser.getLastname() + ")");
        this.setSize(800, 400);
        menuCustomers.add(menuCustomersView);
        menuCustomersView.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,
                KeyEvent.CTRL_MASK + KeyEvent.SHIFT_DOWN_MASK));
        menuCustomers.setMnemonic('v');

        menuMeetings.add(menuMeetingsView);
        menuMeetingsView.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M,
                KeyEvent.CTRL_MASK + KeyEvent.SHIFT_DOWN_MASK));
        menuMeetings.setMnemonic('m');

        this.setLocationRelativeTo(null);

        bar.add(menuCustomers);
        bar.add(menuMeetings);

        this.setJMenuBar(bar);

    }

    public void addMenuMeetingsViewListener(ActionListener al) {
        menuMeetingsView.addActionListener(al);
    }

    public void addMenuCustomersViewListener(ActionListener al) {
        menuCustomersView.addActionListener(al);
    }

}
