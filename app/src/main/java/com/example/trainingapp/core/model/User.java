package com.example.trainingapp.core.model;

public class User {
    public String login;
    public String password;
    public Integer age;
    public String gender;
    public Integer height;
    public Integer weight;
    public Integer needWeight;
    public String uuid;
    public String program;
    public String name;

    public User() { }

    public User(
            String login,
            String password,
            Integer age,
            Integer height,
            Integer weight,
            Integer needWeight,
            String uuid,
            String program,
            String name,
            String gender
    ) {
        this.login = login;
        this.password = password;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.needWeight = needWeight;
        this.uuid = uuid;
        this.program = program;
        this.name = name;
        this.gender = gender;
    }

}
