package com.budgetbox.demo.configuration;

import com.budgetbox.demo.model.entity.Food;
import com.budgetbox.demo.repository.FoodRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Configuration
public class LoadDatabase {

    public static final String FOOD_CSV = "classpath:generic-food.csv";

    @Autowired
    private FoodRepository foodRepository;

    @Bean
    public void initDatabase() throws IOException, CsvValidationException {


        File file = ResourceUtils.getFile(FOOD_CSV);

        BufferedReader reader = Files.newBufferedReader(Paths.get(file.toURI()));
        CSVReader csvReader = new CSVReader(reader);

        String[] nextRecord;
        while ((nextRecord = csvReader.readNext()) != null) {

            Food record = new Food(nextRecord[0], nextRecord[1], nextRecord[2], nextRecord[3]);
            foodRepository.save(record);
        }
    }
}
