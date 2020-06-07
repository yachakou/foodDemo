package com.budgetbox.demo.service;

import com.budgetbox.demo.exception.FoodNonTrouveeException;
import com.budgetbox.demo.mapper.FoodMapper;
import com.budgetbox.demo.model.entity.Food;
import com.budgetbox.demo.model.response.FoodModel;
import com.budgetbox.demo.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private FoodMapper foodMapper;

    @Override
    public FoodModel recupererFoodParId(final Long id) throws FoodNonTrouveeException {
        Optional<Food> resultat = foodRepository.findById(id);
        return resultat.map(food -> foodMapper.mapDbVersModel(food)).orElseThrow(FoodNonTrouveeException::new);
    }

    @Override
    public void modifierFood(@Valid final FoodModel foodModel, final Long id) throws FoodNonTrouveeException {
        Optional<Food> resultat = foodRepository.findById(id);
        if (resultat.isPresent()) {
            Food food = foodMapper.mapModelVersDb(foodModel);
            foodRepository.save(food);
        } else throw new FoodNonTrouveeException();
    }

    @Override
    public List<FoodModel> recupererToutFood() {
        List<Food> resultats = foodRepository.findAll();
        return foodMapper.mapDbsVersModel(resultats);
    }

    @Override
    public FoodModel ajouterFood(@Valid final FoodModel foodModel) {
        Food foodDb = foodMapper.mapModelVersDb(foodModel);
        Food save = foodRepository.save(foodDb);
        return foodMapper.mapDbVersModel(save);
    }
}
