package com.company.Room;

import javax.swing.*;

import com.company.PostgreSQLConnection;
import net.proteanit.sql.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class RoomDetails extends JFrame implements ActionListener {
    JTable roomTable;
    JButton btnSearch, btnAll, btnCLose;
    JComboBox txtAvailabilityStatus, txtCleaningStatus;

    public RoomDetails(){
        setLayout(null);

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(null);
        searchPanel.setBounds(0,0,1000,60);
        add(searchPanel);

        JLabel lblSearchAvailabilityStatus = new JLabel("Availability Status: ");
        lblSearchAvailabilityStatus.setBounds(30,20,120,30);
        searchPanel.add(lblSearchAvailabilityStatus);

        txtAvailabilityStatus = new JComboBox(new String[]{"Available","Occupied"});
        txtAvailabilityStatus.setBackground(Color.WHITE);
        txtAvailabilityStatus.setBounds(150,20,200,30);
        searchPanel.add(txtAvailabilityStatus);

        JLabel lblSearchCleaningStatus = new JLabel("Cleaning Status: ");
        lblSearchCleaningStatus.setBounds(360,20,100,30);
        searchPanel.add(lblSearchCleaningStatus);

        txtCleaningStatus = new JComboBox(new String[]{"Clean","Dirty"});
        txtCleaningStatus.setBackground(Color.WHITE);
        txtCleaningStatus.setBounds(460,20,200,30);
        searchPanel.add(txtCleaningStatus);

        btnSearch = new JButton("Search");
        btnSearch.setBounds(700,20,100,30);
        searchPanel.add(btnSearch);
        btnSearch.addActionListener(this);

        btnAll = new JButton("All");
        btnAll.setBounds(830,20,100,30);
        searchPanel.add(btnAll);
        btnAll.addActionListener(ae -> loadData());

        JPanel columnNames = new JPanel();
        columnNames.setLayout(null);
        columnNames.setBounds(20,90,950,30);
        columnNames.setBackground(Color.WHITE);
        add(columnNames);

        JLabel lblRoomNo = new JLabel("Room No");
        lblRoomNo.setBounds(60,0,1000/5,30);
        columnNames.add(lblRoomNo);

        JLabel lblAvailabilityStatus = new JLabel("Availability Status");
        lblAvailabilityStatus.setBounds(230,0,1000/5,30);
        columnNames.add(lblAvailabilityStatus);

        JLabel lblCleaningStatus = new JLabel("Cleaning Status");
        lblCleaningStatus.setBounds(440,0,1000/5,30);
        columnNames.add(lblCleaningStatus);

        JLabel lblPrice = new JLabel("Price");
        lblPrice.setBounds(630,0,1000/5,30);
        columnNames.add(lblPrice);

        JLabel lblBedType = new JLabel("Bed Type");
        lblBedType.setBounds(4000/5,0,1000/5,30);
        columnNames.add(lblBedType);

        roomTable = new JTable();
        roomTable.setBounds(20,120,950,400);
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

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == btnSearch){
            String availabilityStatus = (String) txtAvailabilityStatus.getSelectedItem();
            String cleaningStatus = (String) txtCleaningStatus.getSelectedItem();

            String query = "Select * from room where availabilitystatus='" + availabilityStatus + "' and cleaningstatus='" + cleaningStatus + "';";
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
    }
}
