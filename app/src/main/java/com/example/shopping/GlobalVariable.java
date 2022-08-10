package com.example.shopping;

public class GlobalVariable {

    private static String token;

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        GlobalVariable.token = token;
    }


}
