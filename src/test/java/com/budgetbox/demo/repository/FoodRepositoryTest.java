package com.budgetbox.demo.repository;

import com.budgetbox.demo.model.entity.Food;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class FoodRepositoryTest {

    @Autowired
    private FoodRepository foodRepository;

    @Test
    public void findbyId(){

        Optional<Food> resultat = foodRepository.findById(2L);

        assertThat(resultat.isPresent(), Matchers.is(true));
        assertThat(resultat.get().getFoodName(), Matchers.equalTo("Pommes"));
    }

    @Test
    public void findAll(){

        List<Food> resultat = foodRepository.findAll();

        assertThat(resultat, Matchers.notNullValue());
        assertThat(resultat, Matchers.hasSize(3));
        assertThat(resultat.get(0).getFoodId(), Matchers.equalTo(1L));
        assertThat(resultat.get(1).getFoodId(), Matchers.equalTo(2L));
        assertThat(resultat.get(2).getFoodId(), Matchers.equalTo(3L));
    }

    @Test
    public void save(){

        Food food = new Food();
        food.setFoodName("Orange");
        food.setFoodGroup("Ronde");
        food.setFoodSubGroup("orange Ronde");
        food.setScientificName("Orangina");

        Food resultat = foodRepository.save(food);

        assertThat(resultat, Matchers.notNullValue());
    }

}