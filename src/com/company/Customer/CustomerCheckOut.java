package com.company.Customer;

import com.company.PostgreSQLConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;

/*
room no(select)

display customer id
display customer name
amt deposited
amt to be paid
amt paid

check out button
cancel button
 */

public class CustomerCheckOut extends JFrame implements ActionListener {

    JTextField txtIdNumber, txtName, txtCheckInDate, txtCheckOutDate, txtNoOfDays, txtDeposit, txtAmtToBePaid, txtAmtPaid;
    JComboBox txtRoomNo;
    JButton btnCheckOut, btnCancel;

    public CustomerCheckOut(){
        setLayout(null);
        setBounds(450,50,1020,680);
        getContentPane().setBackground(Color.WHITE);

        JLabel lblTitle = new JLabel("Check Out");
        lblTitle.setFont(new Font("Times New Roman", Font.BOLD,25));
        lblTitle.setBounds(150,20,200,50);
        add(lblTitle);


        JLabel lblRoomNumber = new JLabel("Room No.");
        lblRoomNumber.setFont(new Font("Times New Roman",Font.PLAIN,18));
        lblRoomNumber.setBounds(60,100,120,30);
        add(lblRoomNumber);

        txtRoomNo = new JComboBox(getRoomNumbers());
        txtRoomNo.setBounds(250,100,200,30);
        add(txtRoomNo);
        txtRoomNo.addActionListener(this);

        JLabel lblIdNumber = new JLabel("Id Number");
        lblIdNumber.setFont(new Font("Times New Roman",Font.PLAIN,18));
        lblIdNumber.setBounds(60,150,120,30);
        add(lblIdNumber);

        txtIdNumber = new JTextField();
        txtIdNumber.setBounds(250,150,200,30);
        add(txtIdNumber);


        JLabel lblName = new JLabel("Customer Name");
        lblName.setFont(new Font("Times New Roman",Font.PLAIN,18));
        lblName.setBounds(60,200,120,30);
        add(lblName);

        txtName = new JTextField();
        txtName.setBounds(250,200,200,30);
        add(txtName);


        JLabel lblCheckInDate = new JLabel("Check-In Date");
        lblCheckInDate.setFont(new Font("Times New Roman",Font.PLAIN,18));
        lblCheckInDate.setBounds(60,250,120,30);
        add(lblCheckInDate);

        txtCheckInDate = new JTextField();
        txtCheckInDate.setBounds(250,250,200,30);
        add(txtCheckInDate);


        JLabel lblCheckOutDate = new JLabel("Check-Out Date");
        lblCheckOutDate.setFont(new Font("Times New Roman",Font.PLAIN,18));
        lblCheckOutDate.setBounds(60,300,120,30);
        add(lblCheckOutDate);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        txtCheckOutDate = new JTextField();
        txtCheckOutDate.setBounds(250,300,200,30);
        add(txtCheckOutDate);
        txtCheckOutDate.setText(df.format(new Date()));


        JLabel lblNoOfDays = new JLabel("Stay Days");
        lblNoOfDays.setFont(new Font("Times New Roman",Font.PLAIN,18));
        lblNoOfDays.setBounds(60, 350,120,30);
        add(lblNoOfDays);

        txtNoOfDays = new JTextField();
        txtNoOfDays.setBounds(250,350,200,30);
        add(txtNoOfDays);


        JLabel lblDeposit = new JLabel("Deposit");
        lblDeposit.setFont(new Font("Times New Roman",Font.PLAIN,18));
        lblDeposit.setBounds(60,400,120,30);
        add(lblDeposit);

        txtDeposit = new JTextField();
        txtDeposit.setBounds(250,400,200,30);
        add(txtDeposit);


        JLabel lblAmtToBePaid = new JLabel("Amt To Be\nPaid");
        lblAmtToBePaid.setFont(new Font("Times New Roman",Font.PLAIN,18));
        lblAmtToBePaid.setBounds(60,450,120,30);
        add(lblAmtToBePaid);

        txtAmtToBePaid = new JTextField();
        txtAmtToBePaid.setBounds(250,450,200,30);
        add(txtAmtToBePaid);


        JLabel lblAmtPaid = new JLabel("Amt Paid");
        lblAmtPaid.setFont(new Font("Times New Roman",Font.PLAIN,18));
        lblAmtPaid.setBounds(60,500,120,30);
        add(lblAmtPaid);

        txtAmtPaid = new JTextField();
        txtAmtPaid.setBounds(250,500,200,30);
        add(txtAmtPaid);


        btnCheckOut = new JButton("Check Out");
        btnCheckOut.setBounds(100, 560, 100, 40);
        add(btnCheckOut);
        btnCheckOut.addActionListener(this);

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(250, 560, 100, 40);
        add(btnCancel);
        btnCancel.addActionListener(ae -> dispose());

        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("com/company/images/customer.jpg"));
        img = new ImageIcon(img.getImage().getScaledInstance(470,540,Image.SCALE_DEFAULT));
        JLabel background = new JLabel();
        background.setIcon(img);
        background.setBounds(500,30,470,540);
        add(background);

        txtRoomNo.setSelectedItem(txtRoomNo.getSelectedItem());
        setVisible(true);
    }

    private String[] getRoomNumbers(){
        ArrayList<String> roomNumberChoices = new ArrayList<>();
        try{
            Connection conn = PostgreSQLConnection.getConnection();
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("Select roomno from room where availabilitystatus='Occupied'");
            while(rs.next()){
                roomNumberChoices.add(rs.getString("roomno"));
            }
            if(roomNumberChoices.size() == 0)
                roomNumberChoices.add("No rooms available");
            s.close();
            conn.close();
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error Getting Room Numbers");
            //new AddCustomer();
        }

        return roomNumberChoices.toArray(new String[]{});
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            Connection conn = PostgreSQLConnection.getConnection();
            Statement s = conn.createStatement();

            if(ae.getSource() == txtRoomNo) {
                String roomNo = (String) txtRoomNo.getSelectedItem();
                ResultSet rs = s.executeQuery("Select * from customer where roomno=" + roomNo);
                if(rs.next()){
                    txtIdNumber.setText(rs.getString("idnumber"));
                    txtName.setText(rs.getString("name"));
                    txtCheckInDate.setText(rs.getString("checkindate"));
                    txtDeposit.setText(rs.getString("deposit"));
                }
                else{
                    JOptionPane.showMessageDialog(null, "No Customers for Check out");
                    dispose();
                }

                rs = s.executeQuery("Select * from room where roomno=" + roomNo);
                if(rs.next()){
                    float roomPrice = rs.getFloat("price");
                    float deposit = Float.parseFloat((String) txtDeposit.getText());
                    float amtToBeDeposited = roomPrice - deposit;
                    txtAmtToBePaid.setText(String.valueOf(amtToBeDeposited));
                }
                else
                    JOptionPane.showMessageDialog(null, "Error getting room data");

                s.close();
                conn.close();
            }
            else if(ae.getSource() == btnCheckOut){
                String roomNo = (String) txtRoomNo.getSelectedItem();
                String idNumber = txtIdNumber.getText();
                String checkOutDate = txtCheckOutDate.getText();

                int noOfDays = 0;
                if(isPositiveInteger(txtNoOfDays.getText()))
                    noOfDays = Integer.parseInt(txtDeposit.getText());
                else {
                    JOptionPane.showMessageDialog(null,"Enter valid no of days");
                    return;
                }

                float deposit = 0;
                if(isPositiveInteger(txtDeposit.getText()))
                    deposit = Float.parseFloat(txtDeposit.getText());
                else {
                    JOptionPane.showMessageDialog(null,"Enter valid deposit amount");
                    return;
                }

                float amountPaid = 0;
                if(isPositiveInteger(txtAmtPaid.getText()))
                    amountPaid = Float.parseFloat(txtAmtPaid.getText());
                else
                    JOptionPane.showMessageDialog(null,"Enter valid deposit amount");



                float finalDeposit = deposit + amountPaid;
                String customerQuery = "Update customer set status='Checked-Out', deposit=" + finalDeposit + ",checkOutDate='" + checkOutDate + "' where idnumber='" + idNumber + "',staydays=" + noOfDays + ";";
                System.out.println(customerQuery);
                int customerRowsAffected = s.executeUpdate(customerQuery);
                if(customerRowsAffected == 1) {
                    String roomQuery = "update room set availabilitystatus='Available', cleaningstatus='Dirty' where roomno=" + roomNo + ";";
                    int roomRowsAffected = s.executeUpdate(roomQuery);
                    if (roomRowsAffected == 1){
                        JOptionPane.showMessageDialog(null, "Check Out Successful");
                        dispose();
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Error adding Room");
                        return;
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Error adding Customer");
                    return;
                }

            }
            else if(ae.getSource() == btnCancel)
                dispose();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }

    public boolean isPositiveInteger(String str){
        if(str.equals(""))
            return false;

        for(char ch: str.toCharArray()){
            if(ch < '0' || ch > '9')
                if(ch != '.')
                    return false;
        }
        return true;
    }

}
