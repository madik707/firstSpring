package com.example.springdemo.repositories;

import com.example.springdemo.entities.ShopItems;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Transactional
public interface ShopItemsRepository extends JpaRepository<ShopItems, Long> {

    List<ShopItems> findAllByAmountGreaterThanOrderByPriceDesc(int amount);

}
