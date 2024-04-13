package com.app.blog.utils;

import org.springframework.beans.BeanUtils;

import java.lang.reflect.Constructor;

public class ObjectMapperUtils {


    public static <T> T mapEntity(Object entity, Class<T> mappedTo){

        try {
            Constructor<T> declaredConstructor = mappedTo.getDeclaredConstructor();
            T object = declaredConstructor.newInstance();
            BeanUtils.copyProperties(entity,object);
            return object;
        } catch (Exception e) {
            throw new RuntimeException("Failed to map Object from " + entity.getClass().getName() + " to Target Object " + mappedTo.getName());
        }
    }
}
