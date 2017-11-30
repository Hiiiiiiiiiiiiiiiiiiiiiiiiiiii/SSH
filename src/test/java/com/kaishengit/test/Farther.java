package com.kaishengit.test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class Farther<T,PK> {

    public Farther(){
        System.out.println("createFather");
//        Class clazz = this.getClass();
//        Class superClazz = clazz.getSuperclass();
//        System.out.println(superClazz);
//        //class com.kaishengit.test.Farther

        Class clazz = getClass();
        ParameterizedType parameterizedType= (ParameterizedType) clazz.getGenericSuperclass();
        //getGenericSuperclass 返回表示此Class所表示的实体(类，接口，基本类型或void)的直接超类的Type
        System.out.println(parameterizedType);
        //com.kaishengit.test.Farther<java.lang.String, java.lang.Integer>
        Type[] types = parameterizedType.getActualTypeArguments();
        //getActualTypeArguments 返回表示此类型实际类型参数的 Type 对象的数组
        System.out.println(types[0]+"     "+types[1]);
        //class java.lang.String       class java.lang.Integer
    }
}







