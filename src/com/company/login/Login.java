package com.company.login;

import com.company.Dashboard;
import com.company.PostgreSQLConnection;
import com.company.Home;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login extends JFrame implements ActionListener {

    JLabel usernameLabel, passwordLabel;
    JTextField usernameInput;
    JPasswordField passwordInput;
    JButton loginButton;

    public Login(){

        //Build Form

        usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50,50,100, 50);
        usernameLabel.setForeground(Color.WHITE);
        add(usernameLabel);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50,100,100, 50);
        passwordLabel.setForeground(Color.WHITE);
        add(passwordLabel);

        usernameInput = new JTextField();
        usernameInput.setBounds(150,65,150, 20);
        add(usernameInput);

        passwordInput = new JPasswordField();
        passwordInput.setBounds(150,115,150, 20);
        add(passwordInput);

        loginButton = new JButton("Login");
        loginButton.setBounds(150,160,80,40);
        add(loginButton);

        setLayout(null);
        getContentPane().setBackground(new Color(96,94,134));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500,200,500,400);
        setVisible(true);

        loginButton.addActionListener(this);  // Call performed action on click
    }

    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == loginButton) {
            if(login(usernameInput.getText(), passwordInput.getText())) {
                new Dashboard();
                dispose();
            }
            else
                JOptionPane.showMessageDialog(null, "Invalid username and password");
        }
    }

    private boolean login(String username, String password){

        String query = "select * from admin where username='" + username + "' and password='" + password + "';";
        try{
            Connection conn = PostgreSQLConnection.getConnection();
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(query);

            if(rs.next()) {
                LoginSession.Username = rs.getString("username");
                LoginSession.isLoggedIn = true;
                conn.close();
                return true;
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        return false;
    }
}
