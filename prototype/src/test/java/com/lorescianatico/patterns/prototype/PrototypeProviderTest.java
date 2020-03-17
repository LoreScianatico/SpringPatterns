package com.lorescianatico.patterns.prototype;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrototypeProviderTest {

    private PrototypeProvider prototypeProvider = new PrototypeProvider();

    private TestClass testClass = new TestClass();

    @BeforeEach
    void setup(){
        prototypeProvider.storePrototype(testClass);
    }

    @Test
    void getPrototype() {
        assertNotNull(prototypeProvider.getPrototype(TestClass.class));
        assertThrows(UnsupportedPrototypeException.class, () -> prototypeProvider.getPrototype(String.class));
    }

    @Test
    void storePrototype() {
        var clone = prototypeProvider.getPrototype(TestClass.class);
        assertNotNull(clone);
        assertNotEquals(testClass, clone);
    }
}