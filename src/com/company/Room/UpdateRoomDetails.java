package com.company.Room;

import com.company.PostgreSQLConnection;
import com.company.Reception;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class UpdateRoomDetails extends JFrame implements ActionListener {
    JTextField txtPrice;
    JComboBox txtRoomNo, txtAvailabilityStatus, txtCleaningStatus, txtBedType;
    JButton btnUpdate, btnCancel;

    public UpdateRoomDetails(){
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setBounds(700,150,500,500);

        JLabel lblAddRooms = new JLabel("Update Room");
        lblAddRooms.setFont(new Font("",Font.BOLD, 20));
        lblAddRooms.setBounds(180,20,200,30);
        add(lblAddRooms);

        JLabel lblRoomNo = new JLabel("Room Number");
        lblRoomNo.setFont(new Font("Times New Roman",Font.PLAIN, 20));
        lblRoomNo.setBounds(60,80,150,30);
        add(lblRoomNo);

        txtRoomNo = new JComboBox(getRoomNumbers());
        txtRoomNo.setBackground(Color.WHITE);
        txtRoomNo.setBounds(250,80, 150, 30);
        add(txtRoomNo);
        txtRoomNo.addActionListener(this);

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

        btnUpdate = new JButton("Update");
        btnUpdate.setBounds(100,340,100,30);
        btnUpdate.addActionListener(this);
        add(btnUpdate);

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(250,340,100,30);
        btnCancel.addActionListener(this);
        add(btnCancel);

        txtRoomNo.setSelectedItem(txtRoomNo.getSelectedItem());

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae){
        try {
            Connection conn = PostgreSQLConnection.getConnection();
            Statement s = conn.createStatement();

            if(ae.getSource() == txtRoomNo) {
                String roomNo = (String) txtRoomNo.getSelectedItem();
                ResultSet rs = s.executeQuery("Select * from room where roomno=" + roomNo);
                if(rs.next()){
                    txtAvailabilityStatus.setSelectedItem(rs.getString("availabilitystatus"));
                    txtCleaningStatus.setSelectedItem("cleaningstatus");
                    txtPrice.setText(rs.getString("price"));
                    txtBedType.setSelectedItem(rs.getString("bedtype"));
                }
            }
            else if(ae.getSource() == btnUpdate){
                String roomNo = (String) txtRoomNo.getSelectedItem();
                float price = 0;
                String availabilityStatus = (String) txtAvailabilityStatus.getSelectedItem();
                String cleaningStatus = (String) txtCleaningStatus.getSelectedItem();
                String bedType = (String) txtBedType.getSelectedItem();

                if(isPositiveNumber(txtPrice.getText()))
                    price = Integer.parseInt(txtPrice.getText());
                else{
                    JOptionPane.showMessageDialog(null, "Enter valid price!");
                    return;
                }

                String query = "update room set availabilitystatus='" + availabilityStatus + "',cleaningstatus='" + cleaningStatus + "',price=" + price + ",bedtype='" + bedType + "' where roomno=" + roomNo + ";";
                int rowsAffected = s.executeUpdate(query);
                if(rowsAffected == 1){
                    JOptionPane.showMessageDialog(null,"Updated room successfully");
                    dispose();
                }
                else {
                    JOptionPane.showMessageDialog(null,"Error in updating room");
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

    private String[] getRoomNumbers(){
        ArrayList<String> roomNumberChoices = new ArrayList<>();
        try{
            Connection conn = PostgreSQLConnection.getConnection();
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("Select roomno from room");
            while(rs.next()){
                roomNumberChoices.add(rs.getString("roomno"));
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

