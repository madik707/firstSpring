package com.example.springdemo.repositories;

import com.example.springdemo.entities.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Categories,Long> {

}
