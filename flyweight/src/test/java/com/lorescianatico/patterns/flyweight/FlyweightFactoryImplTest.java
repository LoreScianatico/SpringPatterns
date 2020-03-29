package com.lorescianatico.patterns.flyweight;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class FlyweightFactoryImplTest {

    @Test
    void getWithSupplier() {
        FlyweightFactory<String, String> toUpperCaseFlyweight = new FlyweightFactoryImpl<>();
        List<String> words = List.of("one", "one", "one", "one", "Two", "Two", "two");
        List<String> uppercaseWords = words.stream().map(word -> toUpperCaseFlyweight.get(word, () -> new String(word).toUpperCase())).collect(Collectors.toList());
        assertEquals("ONE",uppercaseWords.get(0));
    }

    @Test
    void getWithFunction() {
        FlyweightFactory<String, String> toUpperCaseFlyweight = new FlyweightFactoryImpl<>();
        List<String> words = List.of("one", "one", "one", "one", "Two", "Two", "two");
        List<String> uppercaseWords = words.stream().map(word -> toUpperCaseFlyweight.get(word, String::toUpperCase)).collect(Collectors.toList());
        assertEquals("ONE",uppercaseWords.get(0));
    }
}