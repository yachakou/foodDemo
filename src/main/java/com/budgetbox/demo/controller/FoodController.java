package com.budgetbox.demo.controller;

import com.budgetbox.demo.exception.FoodNonTrouveeException;
import com.budgetbox.demo.model.response.FoodModel;
import com.budgetbox.demo.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
public class FoodController {

    @Autowired
    private FoodService foodService;

    @GetMapping(value = "/foods/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FoodModel> consulterElementParId(@PathVariable("id") Long id) {
        try {
            FoodModel resultat = foodService.recupererFoodParId(id);
            return ResponseEntity.ok(resultat);
        } catch (final FoodNonTrouveeException fnte) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/foods/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FoodModel>> consulterToutElements() {
        return ResponseEntity.ok(foodService.recupererToutFood());
    }

    @PutMapping(value = "/foods/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FoodModel> consulterToutElements(@PathVariable("id") Long id,
                                                           @Valid @RequestBody FoodModel foodModel) {

        try {
            foodService.modifierFood(foodModel, id);
            return ResponseEntity.ok().build();
        } catch (final FoodNonTrouveeException fnte) {
            //dans ce cas on crée la ressource
            return new ResponseEntity<>(foodService.ajouterFood(foodModel), (HttpStatus.CREATED));
        }
    }

}
