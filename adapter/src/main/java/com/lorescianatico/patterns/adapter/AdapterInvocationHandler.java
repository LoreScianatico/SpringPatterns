package com.lorescianatico.patterns.adapter;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

@Slf4j
final class AdapterInvocationHandler implements InvocationHandler {

    private Map<String, Supplier<?>> supplierMap = new HashMap<>();

    private Map<String, Function<?,?>> functionMap = new HashMap<>();

    private Map<String, BiFunction<?,?,?>> biFunctionMap = new HashMap<>();

    @Override
    public Object invoke(Object o, Method method, Object[] objects) {
        String methodName = method.getName();
        Supplier<?> supplier = supplierMap.get(methodName);
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
        logger.error("Unknown adapter method: {}", methodName);
        throw new NotAdaptedMethodException("Unknown adapter method: " + methodName);
    }

    void addMethod(String methodName, Supplier<?> supplier){
        supplierMap.put(methodName, supplier);
    }

    <T, U> void addMethod(String methodName, Function<? super T, ? extends U> function){
        functionMap.put(methodName, function);
    }

    <S, T, U> void addMethod(String methodName, BiFunction<? super T, ? super S, ? extends U> bifunction){
        biFunctionMap.put(methodName, bifunction);
    }
}
