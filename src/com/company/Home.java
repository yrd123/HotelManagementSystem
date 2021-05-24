package com.company;

import com.company.login.Login;
import com.company.login.LoginSession;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class Home extends JFrame {
    JButton btnNext;
    JLabel background, text;

    public Home() {

        background = new JLabel();

        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("com/company/images/background.png"));
        background.setIcon(img);
        //background.setBounds(0,0,00,281);

        text = new JLabel("Hotel Management System");
        text.setBounds(10,10,400,50);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("Times New Roman", Font.BOLD, 30));
        background.add(text);

        btnNext = new JButton("Next");
        btnNext.setBounds(550,295,70,40);
        background.add(btnNext);

        add(background);

        btnNext.addActionListener(ae-> {
            new Login();
            dispose();
        });

        setBounds(500,200,500,281);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        System.out.println(LoginSession.isLoggedIn);
    }

}
