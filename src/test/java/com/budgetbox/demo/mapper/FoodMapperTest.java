package com.budgetbox.demo.mapper;

import com.budgetbox.demo.model.entity.Food;
import com.budgetbox.demo.model.response.FoodModel;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;

public class FoodMapperTest {

    private static final long FOOD_ID = 2L;
    private static final String FOOD_GROUP = "group";
    private static final String FOOD_NAME = "name";
    private static final String SUB_GROUP = "subgroup";
    private static final String SCIENTIFIC_NAME = "Scientifique";

    FoodMapperImpl foodMapper = new FoodMapperImpl();

    @Test
    public void mapDbVersModel() {

        Food food = new Food();
        food.setFoodId(FOOD_ID);
        food.setFoodGroup(FOOD_GROUP);
        food.setFoodName(FOOD_NAME);
        food.setFoodSubGroup(SUB_GROUP);
        food.setScientificName(SCIENTIFIC_NAME);


        FoodModel resultat = foodMapper.mapDbVersModel(food);

        assertThat(resultat, Matchers.notNullValue());
        assertThat(resultat.getFoodId(), Matchers.equalTo(FOOD_ID));
        assertThat(resultat.getFoodGroup(), Matchers.equalTo(FOOD_GROUP));
        assertThat(resultat.getFoodName(), Matchers.equalTo(FOOD_NAME));
        assertThat(resultat.getFoodSubGroup(), Matchers.equalTo(SUB_GROUP));
        assertThat(resultat.getScientificName(), Matchers.equalTo(SCIENTIFIC_NAME));
    }

    @Test
    public void mapDbVersModel_valeurNull() {
        FoodModel resultat = foodMapper.mapDbVersModel(null);

        assertThat(resultat, Matchers.nullValue());
    }

    @Test
    public void mapModelVersDb() {

        FoodModel foodModel = new FoodModel();
        foodModel.setFoodId(FOOD_ID);
        foodModel.setFoodGroup(FOOD_GROUP);
        foodModel.setFoodName(FOOD_NAME);
        foodModel.setFoodSubGroup(SUB_GROUP);
        foodModel.setScientificName(SCIENTIFIC_NAME);


        Food resultat = foodMapper.mapModelVersDb(foodModel);

        assertThat(resultat, Matchers.notNullValue());
        assertThat(resultat.getFoodId(), Matchers.equalTo(FOOD_ID));
        assertThat(resultat.getFoodGroup(), Matchers.equalTo(FOOD_GROUP));
        assertThat(resultat.getFoodName(), Matchers.equalTo(FOOD_NAME));
        assertThat(resultat.getFoodSubGroup(), Matchers.equalTo(SUB_GROUP));
        assertThat(resultat.getScientificName(), Matchers.equalTo(SCIENTIFIC_NAME));
    }

    @Test
    public void mapModelVersDb_valeurNull() {
        Food resultat = foodMapper.mapModelVersDb(null);

        assertThat(resultat, Matchers.nullValue());
    }

    @Test
    public void mapDbsVersModel() {
        Food food = new Food();
        List<FoodModel> resultat = foodMapper.mapDbsVersModel(Arrays.asList(food, food));

        assertThat(resultat, Matchers.notNullValue());
        assertThat(resultat, Matchers.hasSize(2));
    }

    @Test
    public void mapDbsVersModel_avecListeNull() {
        List<FoodModel> resultat = foodMapper.mapDbsVersModel(null);

        assertThat(resultat, Matchers.nullValue());
    }


}