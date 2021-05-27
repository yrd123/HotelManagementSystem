package com.company.login;

import com.company.Home;

public class Logout {
    public static void logout(){
        LoginSession.isLoggedIn = false;
        new Home();
    }
}
