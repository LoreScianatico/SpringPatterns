package com.lorescianatico.patterns.adapter;

import java.util.Random;

public class LegacyService {

    private Random random = new Random();

    public Integer returnInt(){
        return random.nextInt();
    }

}
