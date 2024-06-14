package com.example.springdemo.db;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    private Long id;
    private String name;
    private int price;

}
