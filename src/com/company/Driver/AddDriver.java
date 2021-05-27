package com.company.Driver;

import com.company.PostgreSQLConnection;

import javax.swing.*;
import javax.xml.transform.Result;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;

public class AddDriver extends JFrame implements ActionListener {
    JTextField txtName, txtAge, txtCarCompany, txtCarModel, txtLocation;
    JRadioButton male, female;
    ButtonGroup grpGender;
    JComboBox txtStatus;
    JButton btnSubmit;

    public AddDriver(){
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setBounds(400,200,900,520);

        JLabel lblName = new JLabel("Name");
        lblName.setFont(new Font("Times New Roman",Font.PLAIN,20));
        lblName.setBounds(60,30,120,30);
        add(lblName);

        txtName = new JTextField();
        txtName.setBounds(200,30,150,30);
        add(txtName);

        JLabel lblAge = new JLabel("Age");
        lblAge.setFont(new Font("Times New Roman",Font.PLAIN,20));
        lblAge.setBounds(60,80,120,30);
        add(lblAge);

        txtAge = new JTextField();
        txtAge.setBounds(200,80,150,30);
        add(txtAge);

        JLabel lblGender = new JLabel("Gender");
        lblGender.setFont(new Font("Times New Roman",Font.PLAIN,20));
        lblGender.setBounds(60,130,120,30);
        add(lblGender);

        // Gender
        male = new JRadioButton("Male");
        male.setBackground(Color.white);
        male.setBounds(200,130,70,30);
        add(male);

        female = new JRadioButton("Female");
        female.setBackground(Color.white);
        female.setBounds(280,130,70,30);
        add(female);

        grpGender = new ButtonGroup();
        grpGender.add(male);
        grpGender.add(female);

        // Car
        JLabel lblCarCompany = new JLabel("Car Company");
        lblCarCompany.setFont(new Font("Times New Roman",Font.PLAIN,20));
        lblCarCompany.setBounds(60,180,120,30);
        add(lblCarCompany);

        txtCarCompany = new JTextField();
        txtCarCompany.setBounds(200,180,150,30);
        add(txtCarCompany);

        JLabel lblCarModel = new JLabel("Car Model");
        lblCarModel.setFont(new Font("Times New Roman",Font.PLAIN,20));
        lblCarModel.setBounds(60,230,120,30);
        add(lblCarModel);

        txtCarModel = new JTextField();
        txtCarModel.setBounds(200,230,150,30);
        add(txtCarModel);

        // Status
        JLabel lblStatus = new JLabel("Status");
        lblStatus.setFont(new Font("Times New Roman",Font.PLAIN,20));
        lblStatus.setBounds(60,280,120,30);
        add(lblStatus);

        txtStatus = new JComboBox(new String[] {"Available","Busy"});
        txtStatus.setBackground(Color.WHITE);
        txtStatus.setBounds(200,280,150,30);
        add(txtStatus);

        JLabel lblLocation = new JLabel("Location");
        lblLocation.setFont(new Font("Times New Roman",Font.PLAIN,20));
        lblLocation.setBounds(60,330,120,30);
        add(lblLocation);

        txtLocation = new JTextField();
        txtLocation.setBounds(200,330,150,30);
        add(txtLocation);

        btnSubmit = new JButton("Submit");
        btnSubmit.setBackground(Color.black);
        btnSubmit.setForeground(Color.WHITE);
        btnSubmit.setBounds(150,400,150,30);
        add(btnSubmit);
        btnSubmit.addActionListener(this);


        JLabel heading = new JLabel("Add Driver");
        heading.setBounds(550, 10, 400, 30);
        heading.setFont(new Font("",Font.BOLD,20));
        setForeground(Color.black);
        add(heading);

        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("com/company/images/Driver.jpg"));
        img = new ImageIcon(img.getImage().getScaledInstance(450,450,Image.SCALE_DEFAULT));
        JLabel background = new JLabel();
        background.setIcon(img);
        background.setBounds(400,30,450,450);
        add(background);

        //pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == btnSubmit){
            String name, gender, carCompany, carModel, status, location;
            int age = 0;


            name = txtName.getText();

            if(isPositiveInteger(txtAge.getText()))
                age = Integer.parseInt(txtAge.getText());
            else {
                JOptionPane.showMessageDialog(null,"Enter valid age");
                return;
            }

            gender = male.isSelected()? "Male" : "Female";
            carCompany = txtCarCompany.getText();
            carModel = txtCarModel.getText();
            status = (String) txtStatus.getSelectedItem();
            location = txtLocation.getText();

            try{
                Connection conn = PostgreSQLConnection.getConnection();
                Statement s = conn.createStatement();

                int id = (int) Math.floor(Math.random()*1000);
                while(true){
                    ResultSet rs = s.executeQuery("Select id from driver where id=" + id + ";");
                    if(rs.next())
                        id = (int) Math.floor(Math.random()*1000);
                    else
                        break;
                }

                String query = "INSERT INTO DRIVER VALUES ("+ id +",'" + name + "'," + age + ",'" + gender + "','" + carCompany + "','" + carModel + "','" + status + "','" + location + "');";

                int rowsInserted = s.executeUpdate(query);
                if(rowsInserted >= 1)
                    JOptionPane.showMessageDialog(null,"Driver added successfully");
                else
                    JOptionPane.showMessageDialog(null,"Error adding Driver");
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            }
        }
    }

    public boolean isPositiveInteger(String str){
        for(char ch: str.toCharArray()){
            if(ch < '0' || ch > '9')
                return false;
        }
        return true;
    }

}
