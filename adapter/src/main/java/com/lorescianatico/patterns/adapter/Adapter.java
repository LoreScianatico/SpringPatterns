package com.lorescianatico.patterns.adapter;

import java.lang.reflect.Proxy;
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
                new Class[] { adaptInterface },
                adapterInvocationHandler);
    }

    public Adapter<V> adaptMethod(String methodName, Supplier<?> supplier) {
        adapterInvocationHandler.addMethod(methodName, supplier);
        return this;
    }

}
