package com.budgetbox.demo.service;

import com.budgetbox.demo.exception.FoodNonTrouveeException;
import com.budgetbox.demo.model.response.FoodModel;

import javax.validation.Valid;
import java.util.List;

public interface FoodService {

    FoodModel recupererFoodParId(Long id) throws FoodNonTrouveeException;

    void modifierFood(@Valid FoodModel foodModel) throws FoodNonTrouveeException;

    List<FoodModel> recupererToutFood();

    FoodModel ajouterFood(@Valid FoodModel foodModel);
}
