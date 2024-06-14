package com.example.springdemo.services.impls;

import com.example.springdemo.services.TestService;
import org.springframework.stereotype.Service;


@Service
public class TestServiceImpl implements TestService {
    private String data;
    private int intData;

    @Override
    public String getData() {
        return this.data;
    }

    @Override
    public void setData(String data) {
        this.data = data;
    }

    @Override
    public int getIntData() {
        return this.intData;
    }

    @Override
    public void setDataInt(int dataInt) {
        this.intData = dataInt;
    }

    @Override
    public String getFullData(){
        return getData() + " " + getIntData();
    }
}
