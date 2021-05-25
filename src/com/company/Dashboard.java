package com.company;

import com.company.Employee.AddEmployee;
import com.company.Room.AddRoom;
import com.company.login.LoginSession;

import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {
    JMenuBar menuBar;
    JMenu management, admin;
    JMenuItem reception, addRoom, addEmployee, addDriver;

    public Dashboard(){
        setExtendedState(JFrame.MAXIMIZED_BOTH);
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


        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("com/company/images/background.png"));
        JLabel background = new JLabel();
        background.setIcon(img);

        JLabel text = new JLabel("Dashboard");
        text.setBounds(250,50,400,50);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("Times New Roman",Font.BOLD, 30));
        background.add(text);

        add(background);
        pack();
        setVisible(true);
        System.out.println(LoginSession.isLoggedIn);
    }

    public static void main(String[] args){
        new Dashboard();
    }

}
