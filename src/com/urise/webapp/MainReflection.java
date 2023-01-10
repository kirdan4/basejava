package com.urise.webapp;

import com.urise.webapp.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException, InstantiationException {
        Resume r = new Resume();
        Field field = r.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(field.get(r));
        field.set(r, "new_uuid");
        System.out.println(r);

        Method[] methods = r.getClass().getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method);
        }

        Method methodToString = r.getClass().getDeclaredMethod("toString");
        System.out.println(methodToString);

        Method getToStringMethod = (r.getClass().getMethod("toString"));
        Object resultInvoke = getToStringMethod.invoke(r);
        System.out.println(resultInvoke);
    }
}