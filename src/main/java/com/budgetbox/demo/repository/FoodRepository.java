package com.budgetbox.demo.repository;

import com.budgetbox.demo.model.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {

    @Override
    public List<Food> findAll();

    public Optional<Food> findByFoodName(String name);

}
