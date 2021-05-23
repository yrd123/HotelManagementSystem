package com.company.login;

public class Logout {
    public static void logout(){
        LoginSession.isLoggedIn = false;
        new Login();
    }
}
