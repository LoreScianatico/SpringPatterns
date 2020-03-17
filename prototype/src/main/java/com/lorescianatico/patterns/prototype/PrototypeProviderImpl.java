package com.lorescianatico.patterns.prototype;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class PrototypeProviderImpl implements PrototypeProvider {

    private static Map<Class<?>, Object> prototypes = Collections.synchronizedMap(new HashMap<>());

    @Override
    public <T> T getPrototype(Class<T> prototypeClass){
        logger.info("Retrieving prototype for class: {}", prototypeClass.getName());
        T prototype = (T) prototypes.get(prototypeClass);
        if (prototype != null){
            return clonePrototype(prototypeClass, prototype);
        } else {
            String msg = "Prototype not found for class: " + prototypeClass.getName();
            logger.error(msg);
            throw new UnsupportedPrototypeException(msg);
        }
    }

    @Override
    public <T> void storePrototype(T prototype){
        logger.info("Adding prototype for class {}", prototype.getClass().getName());
        prototypes.put(prototype.getClass(), prototype);
    }

    private <T> T clonePrototype(Class<T> prototypeClass, T prototype) {
        try {
            T clone = prototypeClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(clone, prototype);
            return clone;
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
            logger.error("Error while cloning prototype.", e);
            throw new UnsupportedPrototypeException("Error while cloning prototype", e);
        }
    }
}
