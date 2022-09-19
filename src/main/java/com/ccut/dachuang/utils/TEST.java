package com.ccut.dachuang.utils;

import java.util.Random;

public class TEST {

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(new TEST().geCode());
        }
    }
    public String geCode(){
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0;i<6;i++)
        {
            stringBuilder.append(random.nextInt(10));
        }
        return  stringBuilder.toString();
    }
}
