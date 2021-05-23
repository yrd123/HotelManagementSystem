package com.company;

import com.company.login.LoginSession;

import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {
    JMenuBar menuBar;
    JMenu management, admin;
    JMenuItem reception, addRoom, addEmployee, addDriver;
    JLabel background, text;

    public Dashboard(){
        menuBar = new JMenuBar();

        management = new JMenu("Management");
        menuBar.add(management);

        reception = new JMenuItem("Reception");
        management.add(reception);


        admin = new JMenu("Admin");
        admin.setForeground(Color.BLUE);
        menuBar.add(admin);

        addEmployee = new JMenuItem("Add Employee");
        admin.add(addEmployee);

        addRoom = new JMenuItem("Add Room");
        admin.add(addRoom);

        addDriver = new JMenuItem("Add Driver");
        admin.add(addDriver);

        setJMenuBar(menuBar);

        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("com/company/images/background.png"));
        background = new JLabel();
        background.setIcon(img);

        text = new JLabel("Dashboard");
        text.setBounds(250,50,400,50);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("Times New Roman",Font.BOLD, 30));
        background.add(text);

        add(background);

        setBounds(500,200,500,500);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        System.out.println(LoginSession.isLoggedIn);
    }

    public static void main(String[] args){
        new Dashboard();
    }

}
