package com.example.springdemo.repositories;

import com.example.springdemo.entities.Countries;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
@Transactional
public interface CountriesRepository extends JpaRepository<Countries, Long> {
}
