package com.lorescianatico.patterns.adapter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class AdapterInvocationHandler implements InvocationHandler {

    private Map<String, Supplier<?>> supplierMap = new HashMap<>();

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        return supplierMap.get(method.getName()).get();
    }

    void addMethod(String methodName, Supplier<?> supplier){
        supplierMap.put(methodName, supplier);
    }

}
