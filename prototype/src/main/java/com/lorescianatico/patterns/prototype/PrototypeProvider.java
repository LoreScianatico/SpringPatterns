package com.lorescianatico.patterns.prototype;

public interface PrototypeProvider {
    <T> T getPrototype(Class<T> prototypeClass);

    <T> void storePrototype(T prototype);
}
