package com.example.trainingapp.model;

public class CurrentUser {
    public static String login = null;
    public static String password = null;
    public static String gender = null;
    public static Integer age = null;
    public static Integer height = null;
    public static Integer weight = null;
    public static Integer needWeight = null;
    public static String uuid = null;
    public static String program = null;

    public static User getUserFromCurrentUser() {
        return new User(login, password, age, height, weight, needWeight, uuid, program);
    }
}
