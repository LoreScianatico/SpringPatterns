package com.lorescianatico.patterns.prototype;

public class TestClassWithNoPublicConstructor {

    private TestClassWithNoPublicConstructor() {
    }

    public static TestClassWithNoPublicConstructor getInstance(){
        return new TestClassWithNoPublicConstructor();
    }
}
