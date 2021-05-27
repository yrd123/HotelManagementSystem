package com.company.Room;

import com.company.PostgreSQLConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;

public class AddRoom extends JFrame implements ActionListener {
    JTextField txtRoomNo, txtPrice;
    JComboBox txtAvailabilityStatus, txtCleaningStatus, txtBedType;
    JButton btnAddRoom, btnCancel;

    public AddRoom(){
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setBounds(440,200,1000,500);

        JLabel lblAddRooms = new JLabel("Add Rooms");
        lblAddRooms.setFont(new Font("",Font.BOLD, 20));
        lblAddRooms.setBounds(200,20,200,30);
        add(lblAddRooms);

        JLabel lblRoomNo = new JLabel("Room Number");
        lblRoomNo.setFont(new Font("Times New Roman",Font.PLAIN, 20));
        lblRoomNo.setBounds(60,80,150,30);
        add(lblRoomNo);

        txtRoomNo = new JTextField(30);
        txtRoomNo.setBounds(250,80, 150, 30);
        add(txtRoomNo);

        JLabel lblAvailableStatus = new JLabel("Available");
        lblAvailableStatus.setFont(new Font("Times New Roman",Font.PLAIN, 20));
        lblAvailableStatus.setBounds(60,130,150,30);
        add(lblAvailableStatus);

        txtAvailabilityStatus = new JComboBox(new String[]{"Available","Occupied"});
        txtAvailabilityStatus.setBackground(Color.WHITE);
        txtAvailabilityStatus.setBounds(250,130, 150, 30);
        add(txtAvailabilityStatus);

        JLabel lblCleaningStatus = new JLabel("Cleaning Status");
        lblCleaningStatus.setFont(new Font("Times New Roman",Font.PLAIN, 20));
        lblCleaningStatus.setBounds(60,180,150,30);
        add(lblCleaningStatus);

        txtCleaningStatus = new JComboBox(new String[]{"Cleaned","Dirty"});
        txtCleaningStatus.setBackground(Color.WHITE);
        txtCleaningStatus.setBounds(250,180, 150, 30);
        add(txtCleaningStatus);

        JLabel lblPrice = new JLabel("Price");
        lblPrice.setFont(new Font("Times New Roman",Font.PLAIN, 20));
        lblPrice.setBounds(60,230,150,30);
        add(lblPrice);

        txtPrice = new JTextField(30);
        txtPrice.setBounds(250,230, 150, 30);
        add(txtPrice);


        JLabel lblBedType = new JLabel("Bed Type");
        lblBedType.setFont(new Font("Times New Roman",Font.PLAIN, 20));
        lblBedType.setBounds(60,280,150,30);
        add(lblBedType);

        txtBedType = new JComboBox(new String[]{"Single Bed","Double Bed"});
        txtBedType.setBackground(Color.WHITE);
        txtBedType.setBounds(250,280, 150, 30);
        add(txtBedType);


        btnAddRoom = new JButton("Add Room");
        btnAddRoom.setBounds(100,340,100,30);
        btnAddRoom.addActionListener(this);
        add(btnAddRoom);

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(250,340,100,30);
        btnCancel.addActionListener(this);
        add(btnCancel);

        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("com/company/images/room.jpg"));
        img =new ImageIcon(img.getImage().getScaledInstance(500,400,Image.SCALE_DEFAULT));
        JLabel lblImg = new JLabel();
        lblImg.setIcon(img);
        lblImg.setBounds(450,30,500,400);
        add(lblImg);


        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == btnAddRoom){
            int roomNo = 0;
            float price = 0;
            String availabilityStatus = (String) txtAvailabilityStatus.getSelectedItem();
            String cleaningStatus = (String) txtCleaningStatus.getSelectedItem();
            String bedType = (String) txtBedType.getSelectedItem();

            if(isPositiveNumber(txtRoomNo.getText()))
                roomNo = Integer.parseInt(txtRoomNo.getText());
            else{
                JOptionPane.showMessageDialog(null, "Enter valid room number!");
                return;
            }

            if(isPositiveNumber(txtPrice.getText()))
                price = Integer.parseInt(txtPrice.getText());
            else{
                JOptionPane.showMessageDialog(null, "Enter valid price!");
                return;
            }

            String query = "Insert into room values (" + roomNo + ",'" + availabilityStatus + "','" + cleaningStatus + "'," + price + ",'" + bedType + "');";
            try {
                Connection conn = PostgreSQLConnection.getConnection();
                Statement s = conn.createStatement();

                int rowsAffected = s.executeUpdate(query);
                if(rowsAffected == 1){
                    JOptionPane.showMessageDialog(null,"Added room successfully");
                }
            }
            catch (Exception e) {
                JOptionPane.showMessageDialog(null,e);
            }

        }
        else if(ae.getSource() == btnCancel)
            dispose();
    }

    public boolean isPositiveNumber(String str){
        if(str.equals(""))
            return false;
        for(char ch: str.toCharArray()){
            if(ch < '0' || ch > '9'){
                if(ch != '.')
                    return false;
            }
        }
        return true;
    }

}

