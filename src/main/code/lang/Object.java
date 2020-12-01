package lang;

/**
 * Copyright (c) 2020/11/27. libin Inc. All Rights Reserved.
 * Authors: libin <libin>
 * <p>
 * Purpose :
 */
public class Object implements Cloneable {
    public static void main(String[] args) throws CloneNotSupportedException {
        java.lang.Object obj1 = new java.lang.Object();
        java.lang.Object obj2 = new java.lang.Object();
        System.out.println(obj1.hashCode());
        System.out.println(obj1.toString());
        System.out.println(obj1 == obj2);
        System.out.println(obj1.equals(obj2));

        ObjDemo ObjDemo1 = new ObjDemo();
        ObjDemo1.setName("fly");
        ObjDemo ObjDemo2 = ObjDemo1.clone();
        System.out.println("ObjDemo1 == ObjDemo2 " + (ObjDemo1 == ObjDemo2));
        System.out.println("ObjDemo1.equals(ObjDemo2 " + (ObjDemo1.equals(ObjDemo2)));
        System.out.println(ObjDemo1.getName());
        System.out.println(ObjDemo2.getName());
    }
}

class ObjDemo implements Cloneable {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected ObjDemo clone() throws CloneNotSupportedException {
        ObjDemo objDemo = new ObjDemo();
        return (ObjDemo) super.clone();
    }
}
