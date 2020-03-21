package com.lorescianatico.patterns.adapter;

import java.lang.reflect.Proxy;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public final class Adapter<V> {

    private Class<V> adaptInterface;

    private AdapterInvocationHandler adapterInvocationHandler;

    private Adapter(Class<V> adaptInterface, AdapterInvocationHandler adapterInvocationHandler) {
        this.adaptInterface = adaptInterface;
        this.adapterInvocationHandler = adapterInvocationHandler;
    }

    public static <V> Adapter<V> adapt(Class<V> adaptInterface){
        AdapterInvocationHandler h = new AdapterInvocationHandler();
        return new Adapter<>(adaptInterface,h);
    }

    public V build(){
        return (V) Proxy.newProxyInstance(
                Adapter.class.getClassLoader(),
                new Class[] {adaptInterface},
                adapterInvocationHandler);
    }

    public <S> Adapter<V> adaptMethod(String methodName, Supplier<? extends S> supplier) {
        adapterInvocationHandler.addMethod(methodName, supplier);
        return this;
    }

    public <T, U> Adapter<V> adaptMethod(String methodName, Function<? super T, ? extends U> function) {
        adapterInvocationHandler.addMethod(methodName, function);
        return this;
    }

    public <S, T, U> Adapter<V> adaptMethod(String methodName, BiFunction<? super T, ? super S, ? extends U> function) {
        adapterInvocationHandler.addMethod(methodName, function);
        return this;
    }

}
