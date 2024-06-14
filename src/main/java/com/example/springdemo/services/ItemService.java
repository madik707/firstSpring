package com.example.springdemo.services;

import com.example.springdemo.entities.Categories;
import com.example.springdemo.entities.Countries;
import com.example.springdemo.entities.ShopItems;

import java.util.List;

public interface ItemService {
    ShopItems addItem(ShopItems item);
    List<ShopItems> getAllItems();
    ShopItems getItem(Long id);
    void deleteItem(ShopItems item);
    ShopItems saveItem(ShopItems item);



    List<Countries> getAllCountries();
    Countries addCountry(Countries countries);
    Countries saveCountry(Countries countries);
    Countries getCountry(Long id);



    List<Categories> getAllCategories();
    Categories getCategory(Long id);
    Categories saveCategory(Categories category);
    Categories addCategory(Categories category);
    void deleteCategory(Categories categories);


}
