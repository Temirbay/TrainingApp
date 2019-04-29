package com.example.trainingapp.model;

public class User {

    public User(
            String login,
            String password,
            Integer age,
            Integer height,
            Integer weight,
            Integer needWeight,
            String uuid,
            String program
    ) {
        this.login = login;
        this.password = password;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.needWeight = needWeight;
        this.uuid = uuid;
        this.program = program;
    }

    public String login;
    public String password;
    public Integer age;
    public Integer height;
    public Integer weight;
    public Integer needWeight;
    public String uuid;
    public String program;
}
