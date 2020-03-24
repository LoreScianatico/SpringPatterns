package com.lorescianatico.patterns.prototype;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Component
@Slf4j
public final class PrototypeProviderImpl implements PrototypeProvider {

    private static Map<Class<?>, Object> prototypes = Collections.synchronizedMap(new HashMap<>());

    private static Map<Class<?>, Supplier<?>> suppliers = new HashMap<>();

    @Override
    public <T> T getPrototype(Class<T> prototypeClass){
        logger.info("Retrieving prototype for class: {}", prototypeClass.getName());
        T prototype = (T) prototypes.get(prototypeClass);
        if (prototype != null){
            return clonePrototype(prototypeClass, prototype);
        } else {
            String msg = String.format("Prototype not found for class: %s", prototypeClass.getName());
            logger.error(msg);
            throw new UnsupportedPrototypeException(msg);
        }
    }

    @Override
    public <T> void storePrototype(T prototype){
        storePrototype(prototype, getDefaultConstructor(prototype));
    }

    @Override
    public <T> void storePrototype(T prototype, Supplier<T> instantiationMethod) {
        logger.info("Adding prototype for class {}", prototype.getClass().getName());
        prototypes.put(prototype.getClass(), prototype);
        suppliers.put(prototype.getClass(), instantiationMethod);
    }

    private <T> Supplier<T> getDefaultConstructor(T prototype) {
        return () -> {
            try {
                return (T) prototype.getClass().getDeclaredConstructor().newInstance();
            } catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                logger.error("No constructor or instantiation method provided for this prototype.", e);
                throw new UnsupportedPrototypeException("No constructor or instantiation method provided for this prototype", e);
            }
        };
    }

    private <T> T clonePrototype(Class<T> prototypeClass, T prototype) {
        try {
            T clone = (T) suppliers.get(prototypeClass).get();
            BeanUtils.copyProperties(clone, prototype);
            return clone;
        } catch (IllegalAccessException | InvocationTargetException e) {
            logger.error("Error while cloning prototype.", e);
            throw new UnsupportedPrototypeException("Error while cloning prototype", e);
        }
    }
}
