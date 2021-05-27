package com.company;

import com.company.login.Login;
import com.company.login.LoginSession;

import javax.swing.*;
import java.awt.*;

public class Home extends JFrame {
    JButton btnLogIn;
    JLabel background;

    public Home() {

        background = new JLabel();

        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("com/company/images/HMS.jpg"));
        img = new ImageIcon(img.getImage().getScaledInstance(1000,600,Image.SCALE_DEFAULT));
        background.setIcon(img);
        //background.setBounds(0,0,00,281);

        btnLogIn = new JButton("Login");
        btnLogIn.setFont(new Font("Times New Roman", Font.PLAIN, 21));
        btnLogIn.setBounds(840,520,120,60);
        background.add(btnLogIn);
        btnLogIn.addActionListener(ae-> {
            new Login();
            dispose();
        });

        add(background);

        setBounds(300,100,1000,600);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }

}
