package com.example.springdemo.db;

import java.util.ArrayList;

public class DBManager {
    private static ArrayList<Item> items = new ArrayList<>();

    static {
        items.add(new Item(1L,"Iphone 12", 340000));
        items.add(new Item(2L,"Iphone 11", 320000));
        items.add(new Item(3L,"Iphone 13", 360000));
        items.add(new Item(4L,"Nothing Phone", 240000));
    }

    private static Long id = 5L;
    public static void addItem(Item item){
        item.setId(id);
        items.add(item);
        id++;
    }

    public static Item getItem(Long id){
        for (Item item:items){
            if (item.getId() == id){
                return item;
            }
        }
        return null;
    }
    public static void deleteItem(Long id){
        for (Item item:items){
            if (item.getId() == id){
                items.remove(item);
                break;
            }
        }
    }

    public static ArrayList<Item> getItem(){
        return items;
    }

    public static void saveItem(Item item){
        for (Item i: items){
            if (i.equals(item)){
                i.setName(item.getName());
                i.setPrice(item.getPrice());
            }
        }
    }
}
