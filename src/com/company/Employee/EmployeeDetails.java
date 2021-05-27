package com.company.Employee;

import javax.swing.*;

import com.company.PostgreSQLConnection;
import net.proteanit.sql.*;

import java.awt.*;
import java.sql.*;

public class EmployeeDetails extends JFrame{
    JTable employeeTable;
    JButton btnCLose;

    public EmployeeDetails(){
        setLayout(null);
        setBounds(500,50,1000,650);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(null);
        topPanel.setBounds(0,0,1000,30);
        topPanel.setBackground(Color.WHITE);
        add(topPanel);

        JLabel lblId = new JLabel("ID");
        lblId.setBounds(0,0,1000/9,30);
        topPanel.add(lblId);
        
        JLabel lblName = new JLabel("Name");
        lblName.setBounds(1000/9,0,1000/9,30);
        topPanel.add(lblName);

        JLabel lblAge = new JLabel("Age");
        lblAge.setBounds(2000/9,0,1000/9,30);
        topPanel.add(lblAge);

        JLabel lblGender = new JLabel("Gender");
        lblGender.setBounds(3000/9,0,1000/9,30);
        topPanel.add(lblGender);

        JLabel lblJob = new JLabel("Job");
        lblJob.setBounds(4000/9,0,1000/9,30);
        topPanel.add(lblJob);

        JLabel lblSalary = new JLabel("Salary");
        lblSalary.setBounds(5000/9,0,1000/9,30);
        topPanel.add(lblSalary);

        JLabel lblPhoneNo = new JLabel("Phone Number");
        lblPhoneNo.setBounds(6000/9,0,1000/9,30);
        topPanel.add(lblPhoneNo);

        JLabel lblAadhar = new JLabel("Aadhar Number");
        lblAadhar.setBounds(7000/9,0,1000/9,30);
        topPanel.add(lblAadhar);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(8000/9,0,1000/9,30);
        topPanel.add(lblEmail);


        employeeTable = new JTable();
        employeeTable.setBounds(0,30,1000,500);
        add(employeeTable);

        btnCLose = new JButton("Close");
        btnCLose.setBounds(400,560,100,30);
        add(btnCLose);
        btnCLose.addActionListener(ae->dispose());

        setVisible(true);

    }

    private void loadData(String query){
        try{
            Connection conn = PostgreSQLConnection.getConnection();
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(query);

            employeeTable.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error in displaying records!");
            dispose();
        }
    }

    public void displayEmployeeInformation(){
        String query = "Select * from employee;";
        loadData(query);
    }

    public void displayManagerInformation(){
        String query = "Select * from employee where job='Manager';";
        loadData(query);
    }

}
