package com.budgetbox.demo.controller;

import com.budgetbox.demo.exception.FoodNonTrouveeException;
import com.budgetbox.demo.model.response.FoodModel;
import com.budgetbox.demo.service.FoodService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class FoodControllerTest {

    public static final long ID = 1L;
    public static final String URL_FOOD_ID = "/foods/{id}";
    public static final String URL_FOOD = "/foods/";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private FoodService foodService;

    @Test
    public void consulterElementParId_avecResultat() throws Exception {
        FoodModel resultatService = new FoodModel();
        resultatService.setFoodId(50L);
        when(foodService.recupererFoodParId(eq(ID))).thenReturn(resultatService);
        MvcResult mvcResult = mockMvc.perform(get("/foods/{id}", ID))
                .andExpect(status().isOk()).andReturn();

        String resultat = mvcResult.getResponse().getContentAsString();
        String attendu = objectMapper.writeValueAsString(resultatService);

        assertThat(resultat, Matchers.equalTo(attendu));
        verify(foodService).recupererFoodParId(anyLong());
    }

    @Test
    public void consulterElementParId_sansResultat() throws Exception {
        when(foodService.recupererFoodParId(eq(ID))).thenThrow(FoodNonTrouveeException.class);

        mockMvc.perform(get(URL_FOOD_ID, ID))
                .andExpect(status().isNotFound()).andReturn();

        verify(foodService).recupererFoodParId(anyLong());
    }

    @Test
    public void consulterElementParId_IdInvalide() throws Exception {
        mockMvc.perform(get(URL_FOOD_ID, "Invalide"))
                .andExpect(status().isBadRequest()).andReturn();

        verify(foodService, never()).recupererFoodParId(anyLong());
    }

    @Test
    public void consulterToutElements() throws Exception {
        FoodModel resultatService = new FoodModel();
        resultatService.setFoodId(800L);
        List<FoodModel> serviceReponse = Arrays.asList(resultatService);
        when(foodService.recupererToutFood()).thenReturn(serviceReponse);
        MvcResult mvcResult = mockMvc.perform(get(URL_FOOD))
                .andExpect(status().isOk()).andReturn();

        String resultat = mvcResult.getResponse().getContentAsString();
        String attendu = objectMapper.writeValueAsString(serviceReponse);

        assertThat(resultat, Matchers.equalTo(attendu));
        verify(foodService).recupererToutFood();
    }


    @Test
    public void modifierFood_EntreeInvalide() throws Exception {
        FoodModel entree = new FoodModel();

        String bodyEntreeJson = objectMapper.writeValueAsString(entree);

        mockMvc.perform(put(URL_FOOD_ID, ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(bodyEntreeJson))
                .andExpect(status().isBadRequest()).andReturn();

        verify(foodService, never()).modifierFood(any(FoodModel.class), anyLong());
    }

    @Test
    public void modifierFood_Existant() throws Exception {
        FoodModel entree = getFoodModel();

        String bodyEntreeJson = objectMapper.writeValueAsString(entree);

        mockMvc.perform(put(URL_FOOD_ID, ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(bodyEntreeJson))
                .andExpect(status().isOk()).andReturn();

        verify(foodService).modifierFood(any(FoodModel.class), anyLong());
        verify(foodService, never()).ajouterFood(any(FoodModel.class));
    }

    @Test
    public void modifierFood_CreationRessource() throws Exception {
        FoodModel entree = getFoodModel();

        doThrow(FoodNonTrouveeException.class).when(foodService).modifierFood(any(FoodModel.class), any(Long.class));
        when(foodService.ajouterFood(entree)).thenReturn(getFoodModel(10L));

        String bodyEntreeJson = objectMapper.writeValueAsString(entree);

        mockMvc.perform(put(URL_FOOD_ID, ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(bodyEntreeJson))
                .andExpect(status().isCreated())
                .andReturn();

        verify(foodService).modifierFood(any(FoodModel.class), any(Long.class));
        verify(foodService).ajouterFood(any(FoodModel.class));

    }

    private FoodModel getFoodModel() {
        FoodModel entree = new FoodModel();
        entree.setFoodName("Name");
        entree.setFoodGroup("Group");
        entree.setFoodSubGroup("SubGroup");
        return entree;
    }

    private FoodModel getFoodModel(Long id) {
        FoodModel entree = getFoodModel();
        entree.setFoodId(id);
        return entree;
    }

}