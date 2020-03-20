package com.lorescianatico.patterns.adapter;

import java.util.Random;

public class LegacyService {

    private Random random = new Random();

    public Integer returnInt(){
        return random.nextInt();
    }

    public Integer plusOne(Integer i){
        return i + 1;
    }

    public String intToString(Integer i){
        return i.toString();
    }

    public int sumUp(int a, int b){
        return a + b;
    }
}
