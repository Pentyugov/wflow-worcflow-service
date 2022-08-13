package com.pentyugov.wflow.workflowservice.core.util;

import com.pentyugov.wflow.workflowservice.core.domain.BaseEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class EntityUtil {

    public static void copyProperties(Object source, Object target) throws BeansException {
        Class<?> clazz = target.getClass();
        String[] ignoredProperties = getParentClassProperties(clazz).toArray(new String[0]);
        BeanUtils.copyProperties(source, target, ignoredProperties);
    }

    private static  <T> List<String> getParentClassProperties(Class<T> entityClass) {
        List<String> ignoredProperties = new ArrayList<>();
        if (!entityClass.equals(BaseEntity.class)) {
            ignoredProperties.addAll(getParentClassProperties(entityClass.getSuperclass()));
        } else {
            for (Field field : entityClass.getDeclaredFields()) {
                ignoredProperties.add(field.getName());
            }
        }

        return ignoredProperties;
    }
}
