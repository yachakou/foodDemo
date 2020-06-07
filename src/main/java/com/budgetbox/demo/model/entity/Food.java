package com.budgetbox.demo.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Food {

    @Id
    @GeneratedValue
    private Long foodId;

    private String foodName;
    private String scientificName;
    private String foodGroup;
    private String foodSubGroup;

    public Food() {
    }

    public Food(String foodName, String scientificName, String foodGroup, String foodSubGroup) {
        this.foodName = foodName;
        this.scientificName = scientificName;
        this.foodGroup = foodGroup;
        this.foodSubGroup = foodSubGroup;
    }

    public void setFoodId(Long foodId) {
        this.foodId = foodId;
    }

    public Long getFoodId() {
        return foodId;
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

    @Override
    public String toString() {
        return "Food{" +
                "foodId=" + foodId +
                ", foodName='" + foodName + '\'' +
                ", scientificName='" + scientificName + '\'' +
                ", foodGroup='" + foodGroup + '\'' +
                ", foodSubGroup='" + foodSubGroup + '\'' +
                '}';
    }
}
