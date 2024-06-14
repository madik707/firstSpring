package com.example.springdemo.beans;

import org.springframework.stereotype.Component;

@Component
public class TestBean {
    private String text;

    public TestBean(){
        System.out.println("TestBean activated");
    }

    public void setText(String text){
        this.text = text;
    }

    public String getText(){
        return this.text;
    }
}
