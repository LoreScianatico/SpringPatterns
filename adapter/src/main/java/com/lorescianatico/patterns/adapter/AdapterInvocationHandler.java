package com.lorescianatico.patterns.adapter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class AdapterInvocationHandler implements InvocationHandler {

    private Map<String, Supplier<?>> supplierMap = new HashMap<>();

    private Map<String, Function<?,?>> functionMap = new HashMap<>();

    private Map<String, BiFunction<?,?,?>> biFunctionMap = new HashMap<>();

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        String methodName = method.getName();
        Supplier supplier = supplierMap.get(methodName);
        if(supplier != null){
            return supplier.get();
        }
        Function function = functionMap.get(methodName);
        if(function != null){
            return function.apply(objects[0]);
        }
        BiFunction biFunction = biFunctionMap.get(methodName);
        if (biFunction != null){
            return biFunction.apply(objects[0], objects[1]);
        }

        throw new NotAdaptedMethodException("Unknown adapter method: " + methodName + " for class: " + o.getClass().getName());
    }

    void addMethod(String methodName, Supplier<?> supplier){
        supplierMap.put(methodName, supplier);
    }

    void addMethod(String methodName, Function<?,?> function){
        functionMap.put(methodName, function);
    }

    void addMethod(String methodName, BiFunction<?,?,?> bifunction){
        biFunctionMap.put(methodName, bifunction);
    }
}
