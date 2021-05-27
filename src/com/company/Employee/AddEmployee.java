package com.company.Employee;

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

public class AddEmployee extends JFrame implements ActionListener {
    JTextField txtName, txtAge, txtSalary, txtPhone, txtAadhar, txtEmail;
    JRadioButton male, female;
    ButtonGroup grpGender;
    JComboBox txtJob;
    JButton btnSubmit;

    public AddEmployee(){
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

        // Jobs
        JLabel lblJob = new JLabel("Job");
        lblJob.setFont(new Font("Times New Roman",Font.PLAIN,20));
        lblJob.setBounds(60,180,120,30);
        add(lblJob);

        String[] jobs = {"Front Desk Clerks","Porters","Housekeeping","Kitchen Staff","Room Service","Waiter/Waitress","Manager","Accountant","Chef"};
        txtJob = new JComboBox(jobs);
        txtJob.setBackground(Color.WHITE);
        txtJob.setBounds(200,180,150,30);
        add(txtJob);

        JLabel lblSalary = new JLabel("Salary");
        lblSalary.setFont(new Font("Times New Roman",Font.PLAIN,20));
        lblSalary.setBounds(60,230,120,30);
        add(lblSalary);

        txtSalary = new JTextField();
        txtSalary.setBounds(200,230,150,30);
        add(txtSalary);

        JLabel lblPhone = new JLabel("Phone");
        lblPhone.setFont(new Font("Times New Roman",Font.PLAIN,20));
        lblPhone.setBounds(60,280,120,30);
        add(lblPhone);

        txtPhone = new JTextField();
        txtPhone.setBounds(200,280,150,30);
        add(txtPhone);

        JLabel lblAadhar = new JLabel("Aadhar");
        lblAadhar.setFont(new Font("Times New Roman",Font.PLAIN,20));
        lblAadhar.setBounds(60,330,120,30);
        add(lblAadhar);

        txtAadhar = new JTextField();
        txtAadhar.setBounds(200,330,150,30);
        add(txtAadhar);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setFont(new Font("Times New Roman",Font.PLAIN,20));
        lblEmail.setBounds(60,380,120,30);
        add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(200,380,150,30);
        add(txtEmail);


        btnSubmit = new JButton("Submit");
        btnSubmit.setBackground(Color.black);
        btnSubmit.setForeground(Color.WHITE);
        btnSubmit.setBounds(150,430,150,30);
        add(btnSubmit);
        btnSubmit.addActionListener(this);

        JLabel heading = new JLabel("Add Employee Details");
        heading.setBounds(550, 30, 400, 30);
        heading.setFont(new Font("",Font.BOLD,20));
        setForeground(Color.black);
        add(heading);

        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("com/company/images/Employees.jpg"));
        img = new ImageIcon(img.getImage().getScaledInstance(500,500,Image.SCALE_DEFAULT));
        JLabel background = new JLabel();
        background.setIcon(img);
        background.setBounds(400,20,500,500);
        add(background);

        //pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == btnSubmit){
            String name = txtName.getText();
            int age = 0;
            String gender;
            String job = (String) txtJob.getSelectedItem();
            int salary = 0;
            String phone = txtPhone.getText();
            String aadhar = txtAadhar.getText();
            String email = txtEmail.getText();

            if(isPositiveInteger(txtAge.getText()))
                age = Integer.parseInt(txtAge.getText());
            else {
                JOptionPane.showMessageDialog(null,"Enter valid age");
                return;
            }

            if(isPositiveInteger(txtSalary.getText()))
                salary = Integer.parseInt(txtSalary.getText());
            else {
                JOptionPane.showMessageDialog(null,"Enter valid salary");
                return;
            }

            if(male.isSelected())
                gender = "Male";
            else
                gender = "Female";

            try{
                Connection conn = PostgreSQLConnection.getConnection();
                Statement s = conn.createStatement();

                int id = (int) Math.floor(Math.random()*1000);
                while(true){
                    ResultSet rs = s.executeQuery("Select id from employee where id=" + id + ";");
                    if(rs.next())
                        id = (int) Math.floor(Math.random()*1000);
                    else
                        break;
                }

                String query = "INSERT INTO EMPLOYEE VALUES ("+ id +",'" + name + "'," + age + ",'" + gender + "','" + job + "'," + salary + ",'" + phone + "','" + aadhar + "','" + email + "');";

                int rowsInserted = s.executeUpdate(query);
                if(rowsInserted >= 1)
                    JOptionPane.showMessageDialog(null,"Employee added successfully");
                else
                    JOptionPane.showMessageDialog(null,"Error adding employee");
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
