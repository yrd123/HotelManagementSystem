package com.company.Employee;

import javax.swing.*;

public class EmployeeInformation extends JFrame{
    JTable employeeTable;
    JButton btnCLose;

    public EmployeeInformation(){
        setLayout(null);
        employeeTable = new JTable();

        setBounds(500,50,500,500);
        setVisible(true);
    }

    public static void main(String[] args) {
        System.out.println("hi");
    }
}
