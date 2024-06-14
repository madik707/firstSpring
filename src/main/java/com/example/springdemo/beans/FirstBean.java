package com.example.springdemo.beans;

public class FirstBean {
    private String name;
    private int age;

    public FirstBean(){
        System.out.println("Bean activated!!!");
        this.name = "Mal";
        this.age = 0;
    }

    public FirstBean(String name, int age){
        System.out.println("Bean with some stuff is active.");
        this.name = name;
        this.age = age;
    }

    public String getData(){
        return this.name + " is " + this.age + " y.o";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
