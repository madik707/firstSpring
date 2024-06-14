package com.example.springdemo.services.impls;

import com.example.springdemo.entities.Categories;
import com.example.springdemo.entities.Countries;
import com.example.springdemo.entities.ShopItems;
import com.example.springdemo.repositories.CategoryRepository;
import com.example.springdemo.repositories.CountriesRepository;
import com.example.springdemo.repositories.ShopItemsRepository;
import com.example.springdemo.services.ItemService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ShopItemsRepository shopItemsRepository;

    @Autowired
    private CountriesRepository countriesRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ShopItems addItem(ShopItems item) {
        return shopItemsRepository.save(item);
    }

    @Override
    public List<ShopItems> getAllItems() {
        return shopItemsRepository.findAllByAmountGreaterThanOrderByPriceDesc(0);
    }

    @Override
    public ShopItems getItem(Long id) {
        return shopItemsRepository.findById(id).orElseThrow(() -> new RuntimeException(""));
    }

    @Override
    public void deleteItem(ShopItems item) {
        shopItemsRepository.delete(item);
    }

    @Override
    public ShopItems saveItem(ShopItems item) {
        return shopItemsRepository.save(item);
    }

    @Override
    public List<Countries> getAllCountries() {
        return countriesRepository.findAll();
    }

    @Override
    public Countries addCountry(Countries countries) {
        return countriesRepository.save(countries);
    }

    @Override
    public Countries saveCountry(Countries countries) {
        return countriesRepository.save(countries);
    }

    @Override
    public Countries getCountry(Long id) {
        return countriesRepository.getOne(id);
    }

    @Override
    public List<Categories> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Categories getCategory(Long id) {
        return categoryRepository.getOne(id);
    }

    @Override
    public Categories saveCategory(Categories category) {
        return categoryRepository.save(category);
    }

    @Override
    public Categories addCategory(Categories category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Categories categories){
        categoryRepository.delete(categories);
    }
}
