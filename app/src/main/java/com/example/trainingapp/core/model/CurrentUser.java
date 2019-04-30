package com.example.trainingapp.core.model;

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
    public static String name = null;

    public static User getUser() {
        return new User(login, password, age, height, weight, needWeight, uuid, program, name, gender);
    }

    public static void setUser(User user) {
        login = user.login;
        password = user.password;
        gender = user.gender;
        age = user.age;
        height = user.height;
        needWeight = user.needWeight;
        weight = user.weight;
        uuid = user.uuid;
        program = user.program;
        name = user.name;
    }

}
