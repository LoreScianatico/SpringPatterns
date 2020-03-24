package com.lorescianatico.patterns.prototype;

import java.util.function.Supplier;

public interface PrototypeProvider {
    <T> T getPrototype(Class<T> prototypeClass);

    <T> void storePrototype(T prototype);

    <T> void storePrototype(T prototype, Supplier<T> instantiationMethod);
}
