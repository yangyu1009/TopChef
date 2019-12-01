package com.topchef.demo.utils;

import java.util.ArrayList;
import java.util.List;

public class CurrentUser {
    public static List<String> CurrentUserId =new ArrayList<String>();
    public static int getLength(){
        return CurrentUserId.size();
    }
    public static boolean CheckIfLogin(String uid){
        return CurrentUserId.contains(uid);
    }
}
