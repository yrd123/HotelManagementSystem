package com.company.Customer;

import javax.swing.*;

import com.company.PostgreSQLConnection;
import net.proteanit.sql.*;

import java.awt.*;
import java.sql.*;

public class CustomerDetails extends JFrame{
    JTable customerTable;
    JButton btnCLose;

    public CustomerDetails(){
        setLayout(null);
        setBounds(350,50,1100,650);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(null);
        topPanel.setBounds(0,0,1100,30);
        topPanel.setBackground(Color.WHITE);
        add(topPanel);

        JLabel lblId = new JLabel("ID Name");
        lblId.setBounds(0,0,100,30);
        topPanel.add(lblId);

        JLabel lblName = new JLabel("ID Number");
        lblName.setBounds(100,0,100,30);
        topPanel.add(lblName);

        JLabel lblAge = new JLabel("Name");
        lblAge.setBounds(200,0,100,30);
        topPanel.add(lblAge);

        JLabel lblGender = new JLabel("Gender");
        lblGender.setBounds(300,0,100,30);
        topPanel.add(lblGender);

        JLabel lblJob = new JLabel("Phone Number");
        lblJob.setBounds(400,0,100,30);
        topPanel.add(lblJob);

        JLabel lblSalary = new JLabel("Room No");
        lblSalary.setBounds(500,0,100,30);
        topPanel.add(lblSalary);

        JLabel lblPhoneNo = new JLabel("Status");
        lblPhoneNo.setBounds(600,0,100,30);
        topPanel.add(lblPhoneNo);

        JLabel lblAadhar = new JLabel("Deposit");
        lblAadhar.setBounds(700,0,100,30);
        topPanel.add(lblAadhar);

        JLabel lblCheckInDate = new JLabel("CheckIn Date");
        lblCheckInDate.setBounds(800,0,100,30);
        topPanel.add(lblCheckInDate);

        JLabel lblCheckOutDate = new JLabel("Check Out");
        lblCheckOutDate.setBounds(900,0,100,30);
        topPanel.add(lblCheckOutDate);

        JLabel lblNoOfDays = new JLabel("Stay Days");
        lblNoOfDays.setBounds(1000,0,100,30);
        topPanel.add(lblNoOfDays);

        customerTable = new JTable();
        customerTable.setBounds(0,30,1100,500);
        add(customerTable);
        loadData();

        btnCLose = new JButton("Close");
        btnCLose.setBounds(400,560,100,30);
        add(btnCLose);
        btnCLose.addActionListener(ae->dispose());

        setVisible(true);

    }

    private void loadData(){
        try{
            Connection conn = PostgreSQLConnection.getConnection();
            Statement s = conn.createStatement();
            String query = "Select * from customer";
            ResultSet rs = s.executeQuery(query);

            customerTable.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error in displaying records!");
            dispose();
        }
    }

}
