package com.topchef.demo.utils;

import java.util.Random;

public class IDUtils {
    public static String genRecipeId(){
        long millis = System.currentTimeMillis();
        Random random = new Random();
        int end3 = random.nextInt(999);
        String str = millis + String.format("%03d", end3);
        return str;
    }
}
