package com.company;

import com.company.Customer.CustomerCheckIn;
import com.company.Customer.CustomerCheckOut;
import com.company.Customer.CustomerDetails;
import com.company.Driver.DriverDetails;
import com.company.Employee.EmployeeDetails;
import com.company.Room.RoomDetails;
import com.company.Room.UpdateRoomDetails;
import com.company.login.Logout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Reception extends JFrame implements ActionListener {

    JButton btnCheckIn, btnRoomDetails, btnEmployeeDetails, btnCustomerDetails, btnManagerDetails, btnCheckOut,btnUpdateRoomDetails, btnPickUpService, btnDashBoard, btnLogOut;

    public Reception(){
        setLayout(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(93, 157, 248));
        leftPanel.setBounds(0,0,400,screenSize.height);
        leftPanel.setLayout(null);
        add(leftPanel);

        btnCheckIn = new JButton("Customer Check In");
        btnCheckIn.setBackground(new Color(120, 164, 255));
        btnCheckIn.setForeground(Color.white);
        btnCheckIn.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        btnCheckIn.setBounds(10,10,380,50);
        leftPanel.add(btnCheckIn);
        btnCheckIn.addActionListener(this);

        btnCheckOut = new JButton("Customer Check Out");
        btnCheckOut.setBackground(new Color(120,164,255));;
        btnCheckOut.setForeground(Color.white);
        btnCheckOut.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        btnCheckOut.setBounds(10,70,380,50);
        leftPanel.add(btnCheckOut);
        btnCheckOut.addActionListener(this);

        btnCustomerDetails = new JButton("Customer Details");
        btnCustomerDetails.setBackground(new Color(120,164,255));;
        btnCustomerDetails.setForeground(Color.white);
        btnCustomerDetails.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        btnCustomerDetails.setBounds(10,130,380,50);
        leftPanel.add(btnCustomerDetails);
        btnCustomerDetails.addActionListener(this);

        btnRoomDetails = new JButton("Room Details");
        btnRoomDetails.setBackground(new Color(120,164,255));
        btnRoomDetails.setForeground(Color.white);
        btnRoomDetails.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        btnRoomDetails.setBounds(10,190,380,50);
        leftPanel.add(btnRoomDetails);
        btnRoomDetails.addActionListener(this);

        btnUpdateRoomDetails = new JButton("Update Room Details");
        btnUpdateRoomDetails.setBackground(new Color(120,164,255));
        btnUpdateRoomDetails.setForeground(Color.white);
        btnUpdateRoomDetails.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        btnUpdateRoomDetails.setBounds(10,250,380,50);
        leftPanel.add(btnUpdateRoomDetails);
        btnUpdateRoomDetails.addActionListener(this);

        btnEmployeeDetails = new JButton("Employee Details");
        btnEmployeeDetails.setBackground(new Color(120,164,255));
        btnEmployeeDetails.setForeground(Color.white);
        btnEmployeeDetails.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        btnEmployeeDetails.setBounds(10,310,380,50);
        leftPanel.add(btnEmployeeDetails);
        btnEmployeeDetails.addActionListener(this);

        btnManagerDetails = new JButton("Manager Details");
        btnManagerDetails.setBackground(new Color(120,164,255));;
        btnManagerDetails.setForeground(Color.white);
        btnManagerDetails.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        btnManagerDetails.setBounds(10,370,380,50);
        leftPanel.add(btnManagerDetails);
        btnManagerDetails.addActionListener(this);

        btnPickUpService = new JButton("Pick Up Service");
        btnPickUpService.setBackground(new Color(120,164,255));
        btnPickUpService.setForeground(Color.white);
        btnPickUpService.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        btnPickUpService.setBounds(10,430,380,50);
        leftPanel.add(btnPickUpService);
        btnPickUpService.addActionListener(this);

        btnDashBoard = new JButton("DashBoard");
        btnDashBoard.setBackground(new Color(120,164,255));;
        btnDashBoard.setForeground(Color.white);
        btnDashBoard.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        btnDashBoard.setBounds(10,490,380,50);
        leftPanel.add(btnDashBoard);
        btnDashBoard.addActionListener(this);

        btnLogOut = new JButton("LogOut");
        btnLogOut.setBackground(new Color(120,164,255));
        btnLogOut.setForeground(Color.white);
        btnLogOut.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        btnLogOut.setBounds(10,550,380,50);
        leftPanel.add(btnLogOut);
        btnLogOut.addActionListener(this);

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
        if(e.getSource() == btnCheckIn){
            new CustomerCheckIn();
        }
        else if(e.getSource() == btnRoomDetails){
            new RoomDetails();
        }
        else if(e.getSource() == btnEmployeeDetails){
            new EmployeeDetails().displayEmployeeInformation();
        }
        else if(e.getSource() == btnCustomerDetails){
            new CustomerDetails();
        }
        else if(e.getSource() == btnManagerDetails){
            new EmployeeDetails().displayManagerInformation();
        }
        else if(e.getSource() == btnCheckOut){
            new CustomerCheckOut();
        }
        else if(e.getSource() == btnUpdateRoomDetails){
            new UpdateRoomDetails();
        }
        else if(e.getSource() == btnPickUpService){
            new DriverDetails();
        }
        else if(e.getSource() == btnDashBoard){
            new Dashboard();
            dispose();
        }
        else if(e.getSource() == btnLogOut) {
            Logout.logout();
        }
    }
}
