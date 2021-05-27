package com.company.Driver;

import javax.swing.*;

import com.company.PostgreSQLConnection;
import net.proteanit.sql.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class DriverDetails extends JFrame implements ActionListener {
    JTable driverTable;
    JButton btnSearch, btnAll, btnCLose;
    JComboBox txtCarBrand;

    public DriverDetails(){
        setLayout(null);

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(null);
        searchPanel.setBounds(0,0,1000,60);
        add(searchPanel);

        JLabel lblSearchAvailabilityStatus = new JLabel("Car Brands: ");
        lblSearchAvailabilityStatus.setBounds(100,20,150,30);
        searchPanel.add(lblSearchAvailabilityStatus);

        txtCarBrand = new JComboBox(getCarBrands());
        txtCarBrand.setBackground(Color.WHITE);
        txtCarBrand.setBounds(250,20,200,30);
        searchPanel.add(txtCarBrand);

        btnSearch = new JButton("Search");
        btnSearch.setBounds(600,20,100,30);
        searchPanel.add(btnSearch);
        btnSearch.addActionListener(this);

        btnAll = new JButton("All");
        btnAll.setBounds(730,20,100,30);
        searchPanel.add(btnAll);
        btnAll.addActionListener(ae -> loadData());

        JPanel columnNames = new JPanel();
        columnNames.setLayout(null);
        columnNames.setBounds(20,90,950,30);
        columnNames.setBackground(Color.WHITE);
        add(columnNames);

        JLabel lblId = new JLabel("Id");
        lblId.setBounds(50,0,950/8,30);
        columnNames.add(lblId);

        JLabel lblName = new JLabel("Name");
        lblName.setBounds(950/8 + 40,0,950/8,30);
        columnNames.add(lblName);

        JLabel lblAge = new JLabel("Age");
        lblAge.setBounds(1900/8 + 50,0,950/8,30);
        columnNames.add(lblAge);

        JLabel lblGender = new JLabel("Gender");
        lblGender.setBounds(2850/8 + 40,0,950/8,30);
        columnNames.add(lblGender);

        JLabel lblCarCompany = new JLabel("Car Company");
        lblCarCompany.setBounds(3800/8 + 20,0,950/8,30);
        columnNames.add(lblCarCompany);

        JLabel lblCarModel = new JLabel("Car Model");
        lblCarModel.setBounds(4750/8 + 20,0,950/8,30);
        columnNames.add(lblCarModel);

        JLabel status = new JLabel("Status");
        status.setBounds(5700/8 + 40,0,950/8,30);
        columnNames.add(status);

        JLabel location = new JLabel("Location");
        location.setBounds(6650/8 + 30,0,950/8,30);
        columnNames.add(location);

        driverTable = new JTable();
        driverTable.setBounds(20,120,950,400);
        add(driverTable);

        btnCLose = new JButton("Close");
        btnCLose.setBounds(400,560,100,30);
        add(btnCLose);
        btnCLose.addActionListener(ae->dispose());

        loadData();

        setBounds(500,50,1000,650);
        setVisible(true);

    }
    
    private String[] getCarBrands(){
        ArrayList<String> carBrands = new ArrayList<>();
        try{
            Connection conn = PostgreSQLConnection.getConnection();
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("Select car_company from driver");
            while(rs.next()){
                carBrands.add(rs.getString("car_company"));
            }
            s.close();
            conn.close();
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error Getting Car Brands");
            //new AddCustomer();
        }

        return carBrands.toArray(new String[]{});
    }

    private void loadData(){
        String query = "Select * from driver;";
        try{
            Connection conn = PostgreSQLConnection.getConnection();
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(query);

            driverTable.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error in displaying records!");
            dispose();
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == btnSearch){
            String carBrand = (String) txtCarBrand.getSelectedItem();

            String query = "Select * from driver where car_company='" + carBrand + "';";
            try{
                Connection conn = PostgreSQLConnection.getConnection();
                Statement s = conn.createStatement();
                ResultSet rs = s.executeQuery(query);

                driverTable.setModel(DbUtils.resultSetToTableModel(rs));
            }
            catch (Exception e){
                JOptionPane.showMessageDialog(null,"Error in displaying records!");
                dispose();
            }
        }
    }
}
