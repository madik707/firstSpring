package com.example.springdemo.beans;

import org.springframework.stereotype.Component;

@Component
public class SecondBeanImpl implements SecondBean{
    private String data;


    @Override
    public String getData() {
        return this.data;
    }

    @Override
    public void setData(String data) {
        this.data = data;
    }
}
