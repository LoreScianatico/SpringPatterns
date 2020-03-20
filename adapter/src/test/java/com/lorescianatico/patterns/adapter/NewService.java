package com.lorescianatico.patterns.adapter;

public interface NewService {

    Integer getAnInteger();

    Integer addsOne(Integer integer);

    String getSomethingDifferent(Integer integer);

    Integer sum(Integer a, Integer b);

    void unadaptedOne();
}
