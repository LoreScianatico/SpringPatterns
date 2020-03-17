package com.lorescianatico.patterns.utils.predicate;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class PredicateUtilsTest {

    @Test
    void distinctByKey() {
        var list = List.of(new TestClass("a"),new TestClass("a"),new TestClass("a"),new TestClass("b"),new TestClass("b"));
        var filtered = list.stream().filter(PredicateUtils.distinctByKey(TestClass::getField)).collect(Collectors.toList());
        assertEquals(2, filtered.size());
    }
}