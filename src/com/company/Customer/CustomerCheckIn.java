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

public class CustomerCheckIn extends JFrame implements ActionListener {

    JTextField txtIdNumber, txtName, txtPhoneNo, txtStatus, txtCheckInDate, txtCheckOutDate , txtDeposit;
    JComboBox txtIdName, roomNumbers;
    JRadioButton male, female;
    ButtonGroup grpGender;
    JButton btnAdd, btnCancel;

    public CustomerCheckIn(){
        setLayout(null);
        setBounds(450,50,1020,680);
        getContentPane().setBackground(Color.WHITE);

        JLabel lblTitle = new JLabel("Add Customer");
        lblTitle.setFont(new Font("Times New Roman", Font.BOLD,25));
        lblTitle.setBounds(150,20,200,50);
        add(lblTitle);

        JLabel lblIdName = new JLabel("Id Name");
        lblIdName.setFont(new Font("Times New Roman",Font.PLAIN,20));
        lblIdName.setBounds(60,90,120,30);
        add(lblIdName);

        String[] ids = {"Passport","Aadhar", "Voter-ID","Driving License"};
        txtIdName = new JComboBox(ids);
        txtIdName.setBounds(250,90,200,30);
        add(txtIdName);

        JLabel lblIdNumber = new JLabel("Id Number");
        lblIdNumber.setFont(new Font("Times New Roman",Font.PLAIN,20));
        lblIdNumber.setBounds(60,140,120,30);
        add(lblIdNumber);

        txtIdNumber = new JTextField();
        txtIdNumber.setBounds(250,140,200,30);
        add(txtIdNumber);

        JLabel lblName = new JLabel("Full Name");
        lblName.setFont(new Font("Times New Roman",Font.PLAIN,20));
        lblName.setBounds(60,190,120,30);
        add(lblName);

        txtName = new JTextField();
        txtName.setBounds(250,190,200,30);
        add(txtName);

        JLabel lblGender = new JLabel("Gender");
        lblGender.setFont(new Font("Times New Roman",Font.PLAIN,20));
        lblGender.setBounds(60,240,120,30);
        add(lblGender);

        male = new JRadioButton("Male");
        male.setBackground(Color.white);
        male.setBounds(250,240,60,30);
        add(male);

        female = new JRadioButton("Female");
        female.setBackground(Color.white);
        female.setBounds(320,240,100,30);
        add(female);

        grpGender = new ButtonGroup();
        grpGender.add(male);
        grpGender.add(female);


        JLabel lblPhoneNo = new JLabel("Phone No");
        lblPhoneNo.setFont(new Font("Times New Roman",Font.PLAIN,20));
        lblPhoneNo.setBounds(60,290,120,30);
        add(lblPhoneNo);

        txtPhoneNo = new JTextField();
        txtPhoneNo.setBounds(250,290,200,30);
        add(txtPhoneNo);

        JLabel lblRoomNumber = new JLabel("Room No.");
        lblRoomNumber.setFont(new Font("Times New Roman",Font.PLAIN,20));
        lblRoomNumber.setBounds(60,340,120,30);
        add(lblRoomNumber);

        roomNumbers = new JComboBox(getRoomNumbers());
        roomNumbers.setBounds(250,340,200,30);
        add(roomNumbers);

        JLabel lblStatus = new JLabel("Status");
        lblStatus.setFont(new Font("Times New Roman",Font.PLAIN,20));
        lblStatus.setBounds(60,390,120,30);
        add(lblStatus);

        txtStatus = new JTextField();
        txtStatus.setBounds(250,390,200,30);
        add(txtStatus);
        txtStatus.setText("Checked-In");

        // Dates
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        JLabel lblCheckInDate = new JLabel("Check-In Date");
        lblCheckInDate.setFont(new Font("Times New Roman",Font.PLAIN,20));
        lblCheckInDate.setBounds(60,440,120,30);
        add(lblCheckInDate);

        txtCheckInDate = new JTextField();
        txtCheckInDate.setBounds(250,440,200,30);
        add(txtCheckInDate);
        txtCheckInDate.setText(df.format(new Date()));

        /*JLabel lblCheckOutDate = new JLabel("Check-Out Date");
        lblCheckOutDate.setFont(new Font("Times New Roman",Font.PLAIN,20));
        lblCheckOutDate.setBounds(60,490,130,30);
        add(lblCheckOutDate);

        txtCheckOutDate = new JTextField();
        txtCheckOutDate.setBounds(250,490,200,30);
        add(txtCheckOutDate);*/

        JLabel lblDeposit = new JLabel("Deposit");
        lblDeposit.setFont(new Font("Times New Roman",Font.PLAIN,20));
        lblDeposit.setBounds(60,490,120,30);
        add(lblDeposit);

        txtDeposit = new JTextField();
        txtDeposit.setBounds(250,490,200,30);
        add(txtDeposit);

        btnAdd = new JButton("Add");
        btnAdd.setBounds(100, 550, 100, 40);
        add(btnAdd);
        btnAdd.addActionListener(this);

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(250, 550, 100, 40);
        add(btnCancel);
        btnCancel.addActionListener(ae -> dispose());

        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("com/company/images/customer.jpg"));
        img = new ImageIcon(img.getImage().getScaledInstance(470,540,Image.SCALE_DEFAULT));
        JLabel background = new JLabel();
        background.setIcon(img);
        background.setBounds(500,30,470,540);
        add(background);

        setVisible(true);
    }

    private String[] getRoomNumbers(){
        ArrayList<String> roomNumberChoices = new ArrayList<>();
        try{
            Connection conn = PostgreSQLConnection.getConnection();
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("Select roomno from room where availabilitystatus='Available'");
            while(rs.next()){
                roomNumberChoices.add(rs.getString("roomno"));
            }
            if(roomNumberChoices.size() == 0) {
                roomNumberChoices.add("No rooms available");
            }
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
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnAdd){
            String idName = (String) txtIdName.getSelectedItem();
            String idNumber = txtIdNumber.getText();
            String name = txtName.getText();
            String gender = male.isSelected()? "Male" : "Female";
            String phoneNo = txtPhoneNo.getText();
            String roomNo = (String) roomNumbers.getSelectedItem();
            String status = txtStatus.getText();
            String checkInDate = txtCheckInDate.getText();

            float deposit = 0;
            if(isPositiveInteger(txtDeposit.getText()))
                deposit = Float.parseFloat(txtDeposit.getText());
            else {
                JOptionPane.showMessageDialog(null,"Enter valid deposit amount");
                return;
            }

            try{
                Connection conn = PostgreSQLConnection.getConnection();
                Statement s = conn.createStatement();

                String query1 = "select name from customer where idnumber='" + idNumber + "';";
                ResultSet rs = s.executeQuery(query1);
                if(rs.next()){
                    JOptionPane.showMessageDialog(null, "Customer already exists");
                    return;
                }

                String customerQuery = "insert into customer values ('" + idName + "','" + idNumber + "','" + name + "','" + gender + "','" + phoneNo + "'," + roomNo + ",'" + status + "'," + deposit + ",'" + checkInDate + "'," + null +");";
                int customerRowsAffected = s.executeUpdate(customerQuery);

                if(customerRowsAffected == 1) {
                    String roomQuery = "update room set availabilitystatus = 'Occupied' where roomno=" + roomNo + ";";
                    int roomRowsAffected = s.executeUpdate(roomQuery);
                    if (roomRowsAffected == 1){
                        JOptionPane.showMessageDialog(null, "New Customer added");
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
                s.close();
                conn.close();
            }
            catch(Exception ee){
                JOptionPane.showMessageDialog(null, ee);
            }
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
