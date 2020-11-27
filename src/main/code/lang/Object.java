package lang;

/**
 * Copyright (c) 2020/11/27. libin Inc. All Rights Reserved.
 * Authors: libin <libin>
 * <p>
 * Purpose :
 */
public class Object {
    public static void main(String[] args) {
        java.lang.Object obj1 = new java.lang.Object();
        java.lang.Object obj2 = new java.lang.Object();
        System.out.println(obj1.hashCode());
        System.out.println(obj1.toString());
        System.out.println(obj1 == obj2);
        System.out.println(obj1.equals(obj2));

    }
}
