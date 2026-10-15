package com.example.project;

public class MyProperties {
    private static MyProperties mInstance= null;

    public String username;

    protected MyProperties(){}

    public static synchronized MyProperties getInstance() {
        if(null == mInstance){
            mInstance = new MyProperties();
        }
        return mInstance;
    }
}