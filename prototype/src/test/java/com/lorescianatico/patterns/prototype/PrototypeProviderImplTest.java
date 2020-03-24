package com.lorescianatico.patterns.prototype;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrototypeProviderImplTest {

    private PrototypeProviderImpl prototypeProviderImpl = new PrototypeProviderImpl();

    private TestClass testClass = new TestClass();

    @BeforeEach
    void setup(){
        prototypeProviderImpl.storePrototype(testClass);
    }

    @Test
    void getPrototype() {
        assertNotNull(prototypeProviderImpl.getPrototype(TestClass.class));
        assertThrows(UnsupportedPrototypeException.class, () -> prototypeProviderImpl.getPrototype(String.class));
        prototypeProviderImpl.storePrototype(TestClassWithNoPublicConstructor.getInstance());
        assertThrows(UnsupportedPrototypeException.class, () -> prototypeProviderImpl.getPrototype(TestClassWithNoPublicConstructor.class));
        prototypeProviderImpl.storePrototype(TestClassWithNoPublicConstructor.getInstance(), TestClassWithNoPublicConstructor::getInstance);
        assertNotNull(prototypeProviderImpl.getPrototype(TestClassWithNoPublicConstructor.class));
    }

    @Test
    void storePrototype() {
        var clone = prototypeProviderImpl.getPrototype(TestClass.class);
        assertNotNull(clone);
        assertNotEquals(testClass, clone);
    }
}