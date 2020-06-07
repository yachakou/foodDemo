package com.budgetbox.demo.model.response;


import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class FoodModel implements Serializable {

    private Long foodId;
    @NotNull
    private String foodName;
    private String scientificName;
    @NotNull
    private String foodGroup;
    @NotNull
    private String foodSubGroup;

    public FoodModel() {
    }

    public FoodModel(String foodName, String scientificName, String foodGroup, String foodSubGroup) {
        this.foodName = foodName;
        this.scientificName = scientificName;
        this.foodGroup = foodGroup;
        this.foodSubGroup = foodSubGroup;
    }

    public Long getFoodId() {
        return foodId;
    }

    public void setFoodId(Long foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getFoodGroup() {
        return foodGroup;
    }

    public void setFoodGroup(String group) {
        this.foodGroup = group;
    }

    public String getFoodSubGroup() {
        return foodSubGroup;
    }

    public void setFoodSubGroup(String subGroup) {
        this.foodSubGroup = subGroup;
    }
}
