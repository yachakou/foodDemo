package com.budgetbox.demo.mapper;


import com.budgetbox.demo.model.entity.Food;
import com.budgetbox.demo.model.response.FoodModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FoodMapper {

    FoodModel mapDbVersModel(Food input);

    List<FoodModel> mapDbsVersModel(List<Food> input);

    @Mapping(source = "foodId", target = "foodId")
    Food mapModelVersDb(FoodModel input);
}
