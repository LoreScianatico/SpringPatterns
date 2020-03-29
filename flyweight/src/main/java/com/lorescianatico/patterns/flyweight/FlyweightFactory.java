package com.lorescianatico.patterns.flyweight;

import java.util.function.Function;
import java.util.function.Supplier;

public interface FlyweightFactory<T, V> {

    T get(V key, Supplier<T> supplier);

    T get(V key, Function<V,T> producerFunction);

}
