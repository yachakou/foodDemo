package com.budgetbox.demo.service;

import com.budgetbox.demo.exception.FoodNonTrouveeException;
import com.budgetbox.demo.mapper.FoodMapperImpl;
import com.budgetbox.demo.model.entity.Food;
import com.budgetbox.demo.model.response.FoodModel;
import com.budgetbox.demo.repository.FoodRepository;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FoodServiceImplTest {

    public static final long ID = 1L;
    public static final FoodModel FOOD_MODEL = new FoodModel();
    public static final Food DB_MODEL = new Food();

    @Mock
    private FoodMapperImpl foodMapper;
    @Mock
    private FoodRepository foodRepository;
    @InjectMocks
    private FoodServiceImpl foodService;

    @Before
    public void init() {
        when(foodMapper.mapDbVersModel(any(Food.class))).thenReturn(FOOD_MODEL);
        when(foodMapper.mapModelVersDb(any(FoodModel.class))).thenReturn(DB_MODEL);
        when(foodRepository.findById(ID)).thenReturn(Optional.of(DB_MODEL));
    }

    @Test
    public void recupererFoodParId_avecResultat() throws FoodNonTrouveeException {

        FoodModel resultat = foodService.recupererFoodParId(ID);

        verify(foodRepository).findById(eq(ID));
        assertThat(resultat, Matchers.equalTo(FOOD_MODEL));
    }

    @Test(expected = FoodNonTrouveeException.class)
    public void recupererFoodParId_sansResultat() throws FoodNonTrouveeException {

        when(foodRepository.findById(ID)).thenReturn(Optional.empty());

        foodService.recupererFoodParId(ID);
    }

    @Test
    public void ajouterFood() {
        when(foodRepository.save(DB_MODEL)).thenReturn(DB_MODEL);

        FoodModel resultat = foodService.ajouterFood(FOOD_MODEL);

        assertThat(resultat, Matchers.equalTo(FOOD_MODEL));
        verify(foodMapper).mapModelVersDb(any(FoodModel.class));
        verify(foodRepository).save(any(Food.class));
        verify(foodMapper).mapDbVersModel(any(Food.class));
    }

    @Test
    public void modifierFood_avecResultat() throws FoodNonTrouveeException {
        when(foodRepository.save(DB_MODEL)).thenReturn(DB_MODEL);

        foodService.modifierFood(FOOD_MODEL, ID);

        verify(foodRepository).findById(eq(ID));
        verify(foodMapper).mapModelVersDb(any(FoodModel.class));
        verify(foodRepository).save(any(Food.class));
    }

    @Test
    public void modifierFood_sansResultat() {
        when(foodRepository.findById(ID)).thenReturn(Optional.empty());

        try {
            foodService.modifierFood(FOOD_MODEL, ID);
            fail("Expected FoodNonTrouveeException.");
        } catch (FoodNonTrouveeException e) {
            verify(foodRepository).findById(eq(ID));
            verify(foodMapper, never()).mapModelVersDb(any(FoodModel.class));
            verify(foodRepository, never()).save(any(Food.class));
        }

    }

    @Test
    public void recupererToutFood() {
        List<Food> resultatRequete = Arrays.asList(DB_MODEL);
        when(foodRepository.findAll()).thenReturn(resultatRequete);
        when(foodMapper.mapDbsVersModel(resultatRequete)).thenReturn(Arrays.asList(FOOD_MODEL));

        List<FoodModel> foodModels = foodService.recupererToutFood();

        assertThat(foodModels, Matchers.hasSize(1));
        verify(foodRepository).findAll();
        verify(foodMapper).mapDbsVersModel(Mockito.<Food>anyList());
    }


}