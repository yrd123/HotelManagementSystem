package com.company;

import com.company.Customer.CustomerCheckIn;
import com.company.Customer.CustomerCheckOut;
import com.company.Customer.CustomerDetail;
import com.company.Employee.EmployeeDetail;
import com.company.Room.RoomDetail;
import com.company.Room.UpdateRoomDetails;
import com.company.login.Logout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Reception extends JFrame implements ActionListener {

    JButton b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12;

    public Reception(){
        setLayout(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(96,94,134));
        leftPanel.setBounds(0,0,400,screenSize.height);
        leftPanel.setLayout(null);
        add(leftPanel);

        b1 = new JButton("Customer Check In");
        b1.setBackground(new Color(96,94,134));
        b1.setForeground(Color.white);
        b1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        b1.setBounds(0,0,400,50);
        leftPanel.add(b1);
        b1.addActionListener(this);

        b2 = new JButton("Room Details");
        b2.setBackground(new Color(96,94,134));
        b2.setForeground(Color.white);
        b2.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        b2.setBounds(0,50,400,50);
        leftPanel.add(b2);
        b2.addActionListener(this);

        b3 = new JButton("Department");
        b3.setBackground(new Color(96,94,134));;
        b3.setForeground(Color.white);
        b3.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        b3.setBounds(0,100,400,50);
        leftPanel.add(b3);

        b4 = new JButton("Employee Details");
        b4.setBackground(new Color(96,94,134));
        b4.setForeground(Color.white);
        b4.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        b4.setBounds(0,150,400,50);
        leftPanel.add(b4);
        b4.addActionListener(this);

        b5 = new JButton("Customer Details");
        b5.setBackground(new Color(96,94,134));;
        b5.setForeground(Color.white);
        b5.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        b5.setBounds(0,200,400,50);
        leftPanel.add(b5);
        b5.addActionListener(this);

        b6 = new JButton("Manager Details");
        b6.setBackground(new Color(96,94,134));;
        b6.setForeground(Color.white);
        b6.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        b6.setBounds(0,250,400,50);
        leftPanel.add(b6);
        b6.addActionListener(this);

        b7 = new JButton("Check Out");
        b7.setBackground(new Color(96,94,134));;
        b7.setForeground(Color.white);
        b7.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        b7.setBounds(0,300,400,50);
        leftPanel.add(b7);
        b7.addActionListener(this);

        b8 = new JButton("Update Check In");
        b8.setBackground(new Color(96,94,134));;
        b8.setForeground(Color.white);
        b8.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        b8.setBounds(0,350,400,50);
        leftPanel.add(b8);

        b9 = new JButton("Update Room Details");
        b9.setBackground(new Color(96,94,134));
        b9.setForeground(Color.white);
        b9.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        b9.setBounds(0,400,400,50);
        leftPanel.add(b9);
        b9.addActionListener(this);

        b10 = new JButton("Pick Up Service");
        b10.setBackground(new Color(96,94,134));
        b10.setForeground(Color.white);
        b10.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        b10.setBounds(0,450,400,50);
        leftPanel.add(b10);

        b11 = new JButton("Search Room");
        b11.setBackground(new Color(96,94,134));
        b11.setForeground(Color.white);
        b11.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        b11.setBounds(0,500,400,50);
        leftPanel.add(b11);

        b12 = new JButton("LogOut");
        b12.setBackground(new Color(96,94,134));
        b12.setForeground(Color.white);
        b12.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        b12.setBounds(0,550,400,50);
        leftPanel.add(b12);
        b12.addActionListener(ae->{
            Logout.logout();
        });

        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("com/company/images/reception.jpg"));
        img = new ImageIcon(img.getImage().getScaledInstance(screenSize.width - 400, screenSize.height, Image.SCALE_DEFAULT));
        JLabel lblImg = new JLabel(img);
        lblImg.setBounds(400,0,screenSize.width - 400, screenSize.height);
        add(lblImg);

        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        new Reception();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == b1){
            new CustomerCheckIn();
        }
        else if(e.getSource() == b2){
            new RoomDetail();
        }
        else if(e.getSource() == b3){

        }
        else if(e.getSource() == b4){
            new EmployeeDetail().displayEmployeeInformation();
        }
        else if(e.getSource() == b5){
            new CustomerDetail();
        }
        else if(e.getSource() == b6){
            new EmployeeDetail().displayManagerInformation();
        }
        else if(e.getSource() == b7){
            new CustomerCheckOut();
        }
        else if(e.getSource() == b8){

        }
        else if(e.getSource() == b9){
            new UpdateRoomDetails();
        }
        else if(e.getSource() == b10){

        }
        else if(e.getSource() == b11){

        }
        else if(e.getSource() == b12) {

        }
    }
}
