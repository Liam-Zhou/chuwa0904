package com.hw3.loadsequence;

public class LoadSequence {
    static String staticVar = "static variable";
    String var = "normal variable";

    static {
        System.out.println("static block");
    }

    {
        System.out.println("normal block");
    }

    public LoadSequence() {
        System.out.println("constructor");
    }

    public static void main(String[] args) {
        System.out.println(LoadSequence.staticVar);
        LoadSequence ls = new LoadSequence();
        System.out.println(ls.var);
    }
}

