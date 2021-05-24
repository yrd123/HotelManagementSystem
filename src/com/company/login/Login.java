package com.company.login;

import com.company.Dashboard;
import com.company.PostgreSQLConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login extends JFrame implements ActionListener {

    JTextField usernameInput;
    JPasswordField passwordInput;
    JButton btnLogin;

    public Login(){

        //Build Form

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setBounds(50,50,100, 50);
        lblUsername.setForeground(Color.WHITE);
        add(lblUsername);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(50,100,100, 50);
        lblPassword.setForeground(Color.WHITE);
        add(lblPassword);

        usernameInput = new JTextField();
        usernameInput.setBounds(150,65,150, 20);
        add(usernameInput);

        passwordInput = new JPasswordField();
        passwordInput.setBounds(150,115,150, 20);
        add(passwordInput);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(150,160,80,40);
        add(btnLogin);

        setLayout(null);
        getContentPane().setBackground(new Color(96,94,134));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500,200,500,400);
        setVisible(true);

        btnLogin.addActionListener(this);  // Call performed action on click
    }

    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == btnLogin) {
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
