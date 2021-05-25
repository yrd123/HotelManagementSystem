package com.company.Room;

import javax.swing.*;

import com.company.PostgreSQLConnection;
import net.proteanit.sql.*;

import java.awt.*;
import java.sql.*;

public class RoomDetail extends JFrame{
    JTable roomTable;
    JButton btnCLose;

    public RoomDetail(){
        setLayout(null);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(null);
        topPanel.setBounds(0,0,1000,30);
        topPanel.setBackground(Color.WHITE);
        add(topPanel);

        JLabel lblRoomNo = new JLabel("Room No");
        lblRoomNo.setBounds(0,0,1000/5,30);
        topPanel.add(lblRoomNo);

        JLabel lblAvailabilityStatus = new JLabel("Availability Status");
        lblAvailabilityStatus.setBounds(1000/5,0,1000/5,30);
        topPanel.add(lblAvailabilityStatus);

        JLabel lblCleaningStatus = new JLabel("Cleaning Status");
        lblCleaningStatus.setBounds(2000/5,0,1000/5,30);
        topPanel.add(lblCleaningStatus);

        JLabel lblPrice = new JLabel("Price");
        lblPrice.setBounds(3000/5,0,1000/5,30);
        topPanel.add(lblPrice);

        JLabel lblBedType = new JLabel("Bed Type");
        lblBedType.setBounds(4000/5,0,1000/5,30);
        topPanel.add(lblBedType);

        roomTable = new JTable();
        roomTable.setBounds(0,30,1000,500);
        add(roomTable);

        btnCLose = new JButton("Close");
        btnCLose.setBounds(400,560,100,30);
        add(btnCLose);
        btnCLose.addActionListener(ae->dispose());

        loadData();

        setBounds(500,50,1000,650);
        setVisible(true);

    }

    private void loadData(){
        String query = "Select * from room;";
        try{
            Connection conn = PostgreSQLConnection.getConnection();
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(query);

            roomTable.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error in displaying records!");
            dispose();
        }
    }

    public static void main(String[] args) {
        new RoomDetail();
    }

}
