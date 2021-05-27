package com.company;

import com.company.Driver.AddDriver;
import com.company.Employee.AddEmployee;
import com.company.Room.AddRoom;
import com.company.login.LoginSession;
import com.company.login.Logout;

import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {
    JMenuBar menuBar;
    JMenu management, admin;
    JMenuItem reception, addRoom, addEmployee, addDriver;
    JButton btnLogOut;

    public Dashboard(){
        setBounds(450,150,800,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menuBar = new JMenuBar();

        management = new JMenu("Management");
        reception = new JMenuItem("Reception");
        management.add(reception);

        admin = new JMenu("Admin");
        addEmployee = new JMenuItem("Add Employee");
        addRoom = new JMenuItem("Add Room");
        addDriver = new JMenuItem("Add Driver");
        admin.add(addEmployee);
        admin.add(addRoom);
        admin.add(addDriver);
        admin.setForeground(Color.BLUE);

        menuBar.add(management);
        menuBar.add(admin);
        setJMenuBar(menuBar);

        reception.addActionListener(ae -> {
            new Reception();
            dispose();
        });

        addEmployee.addActionListener(ae -> new AddEmployee());
        addRoom.addActionListener(ae -> new AddRoom());
        addDriver.addActionListener(ae-> new AddDriver());


        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("com/company/images/dashboard.jpeg"));
        img = new ImageIcon(img.getImage().getScaledInstance(800,500,Image.SCALE_DEFAULT));
        JLabel background = new JLabel();
        background.setIcon(img);

        JLabel text = new JLabel("Dashboard");
        text.setBounds(40,20,400,50);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("Times New Roman",Font.BOLD, 30));
        background.add(text);

        btnLogOut = new JButton("LogOut");
        btnLogOut.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        btnLogOut.setBounds(670,420,100,50);
        add(btnLogOut);
        btnLogOut.addActionListener(ae ->{
            Logout.logout();
            dispose();
        } );

        add(background);
        pack();
        setVisible(true);
    }

    public static void main(String[] args){
        new Dashboard();
    }

}
