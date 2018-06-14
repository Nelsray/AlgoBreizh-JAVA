/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algobreizh.Views;

import algobreizh.Models.Customer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NewMeetingView extends JFrame {

    private JLabel infoLabel, dateLabel, contactNameLabel, telephoneLabel;
    private JTextField infoText, contactNameText, telephoneText;
    private JButton btnCancel, btnSave;
    private JFormattedTextField dateTextField;
    DateTimeFormatter dateFormat;

    public NewMeetingView(Customer _customer) {
        dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        this.setSize(300, 210);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Nouveau rendez-vous pour " + _customer.getFirstname() + " " + _customer.getLastname());
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.initComponent(_customer);
    }

    private void initComponent(Customer _customer) {

        JPanel panMain = new JPanel();
        panMain.setBackground(Color.white);
        panMain.setPreferredSize(new Dimension(290, 180));
        panMain.setBorder(BorderFactory.createTitledBorder("Saissiez les informations du rendez-vous"));

        dateLabel = new JLabel("Date:");
        panMain.add(dateLabel);

        DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        LocalDateTime datetime = LocalDateTime.now();
        dateTextField = new JFormattedTextField(format);
        dateTextField.setPreferredSize(new Dimension(180, 25));
        dateTextField.setText(datetime.format(dateFormat));

        panMain.add(dateTextField);

        contactNameLabel = new JLabel("Contact:");
        panMain.add(contactNameLabel);
        contactNameText = new JTextField();
        contactNameText.setPreferredSize(new Dimension(160, 25));
        contactNameText.setText(_customer.getFirstname() + " " + _customer.getLastname());
        panMain.add(contactNameText);

        telephoneLabel = new JLabel("Téléphone:");
        panMain.add(telephoneLabel);
        telephoneText = new JTextField();
        telephoneText.setPreferredSize(new Dimension(140, 25));
        panMain.add(telephoneText);

        infoLabel = new JLabel("Informations:");
        panMain.add(infoLabel);
        infoText = new JTextField();
        infoText.setPreferredSize(new Dimension(140, 25));
        panMain.add(infoText);

        btnCancel = new JButton("Quitter");
        panMain.add(btnCancel);

        btnSave = new JButton("Valider");
        panMain.add(btnSave);

        JPanel content = new JPanel();
        content.setBackground(Color.white);
        content.add(panMain);

        this.getContentPane().add(content, BorderLayout.CENTER);
    }

    public void addBtnCancelListener(ActionListener mal) {
        btnCancel.addActionListener(mal);
    }

    public void addBtnSaveListener(ActionListener cal) {
        btnSave.addActionListener(cal);
    }

    public String getContactName() {
        return contactNameText.getText();
    }

    public String getTelephone() {
        return telephoneText.getText();
    }

    public LocalDateTime getMeetingDate() {
        LocalDateTime dateTime = LocalDateTime.from(dateFormat.parse(dateTextField.getText()));
        return dateTime;
    }

    public String getInformations() {
        return infoText.getText();
    }

    public boolean isFilled() {
        return !"".equals(infoText.getText())
                && !"".equals(telephoneText.getText())
                && !"".equals(dateTextField.getText())
                && !"".equals(contactNameText.getText());
    }

}
