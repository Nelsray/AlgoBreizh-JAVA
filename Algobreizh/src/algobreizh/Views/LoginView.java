/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algobreizh.Views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginView extends JFrame {
       
        private JLabel userNameLabel,passwordLabel;
	private JTextField userNameText;
        private JPasswordField passwordText;
        private JButton btnQuit,btnConnect;
        
        public LoginView()
        {
            this.setSize(350, 340);
	    this.setLocationRelativeTo(null);
	    this.setResizable(false);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.initComponent();   
        }

        private void initComponent() 
        {
            
            JPanel panAuth = new JPanel();
	    panAuth.setBackground(Color.white);
	    panAuth.setPreferredSize(new Dimension(340, 310));
	    panAuth.setBorder(BorderFactory.createTitledBorder("Authentification"));
            BufferedImage myPicture = null;
            
            try {
                myPicture = ImageIO.read(new File("/Applications/XAMPP/xamppfiles/htdocs/pf/img/algobreizh.png"));
                JLabel picLabel = new JLabel(new ImageIcon(myPicture));
                panAuth.add(picLabel);
            } catch (IOException ex) {
                Logger.getLogger(LoginView.class.getName()).log(Level.SEVERE, null, ex);
            }
           
            userNameLabel = new JLabel("Nom d'utilisateur:");
            panAuth.add(userNameLabel);
            
	    userNameText = new JTextField();
            userNameText.setPreferredSize(new Dimension(140, 25));
            panAuth.add(userNameText);
            
            passwordLabel = new JLabel("Mot de passe:");
            panAuth.add(passwordLabel);
           
	    passwordText = new JPasswordField();
            passwordText.setEchoChar('*');
            passwordText.setPreferredSize(new Dimension(140, 25));
            panAuth.add(passwordText);
            
            btnQuit = new JButton("Quitter");
            panAuth.add(btnQuit);
             
            btnConnect = new JButton("Connexion");
            panAuth.add(btnConnect);
            
            JPanel content = new JPanel();
	    content.setBackground(Color.white);
	    content.add(panAuth);
            this.getContentPane().add(content, BorderLayout.CENTER);
            
        }
        
        public void addBtnQuitListener(ActionListener mal) {
            btnQuit.addActionListener(mal);
        }
    
	public void addBtnConnectListener(ActionListener cal) {
            btnConnect.addActionListener(cal);
        }
        
        public String getUsername()
        {
            return userNameText.getText();
        }  
         public String getPassword()
        {
            return passwordText.getText();
        }  
}
