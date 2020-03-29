package com.lorescianatico.patterns.flyweight;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

@Slf4j
public final class FlyweightFactoryImpl<T, V> implements FlyweightFactory<T, V> {

    private Map<V, T> flyweightMap = new HashMap<>();

    @Override
    public T get(V key, Supplier<T> supplier) {
        logger.debug("Size before calling: {}", flyweightMap.size());
        T  instance = flyweightMap.computeIfAbsent(key, k -> supplier.get());
        logger.debug("Size after calling: {}", flyweightMap.size());
        return instance;
    }

    @Override
    public T get(V key, Function<V,T> producerFunction) {
        return flyweightMap.computeIfAbsent(key, producerFunction);
    }
}
