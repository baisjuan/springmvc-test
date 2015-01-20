package com.devspark.dietsystem.service;

import com.devspark.dietsystem.dao.PlateDao;
import com.devspark.dietsystem.dto.GeneratedDietDto;
import com.devspark.dietsystem.entity.Plate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.commons.lang3.Validate.notNull;

/**
 * This service generates the diet list retrieved by calories
 *
 * @autor laragno
 */
@Service
public class DietGeneratorService {
    private static final String BREAKFAST = "breakfast";
    private static final String LUNCH = "lunch";
    private static final String SUPPER = "supper";
    private static final String DINNER = "dinner";
    private PlateDao pd;

    @Autowired
    public DietGeneratorService(PlateDao pd) {
        this.pd = notNull(pd, "The Plate DAO could not be null");

    }

    /**
     * Returns an integer value with the specific percentage of calories for each meal
     *
     * @param MaxCalories
     * @param percentage
     * @return
     */
    private int percentageToCalories(int MaxCalories, int percentage) {
        return ((MaxCalories * percentage) / 100);
    }


    /**
     * Picks one random plate from a given list.
     *
     * @param toSelect
     * @return
     */
    private Plate pickRandomPlate(List<Plate> toSelect) {
        int randomIndex = (int) (Math.random() * toSelect.size());
        return toSelect.get(randomIndex);
    }


    /**
     * Executes one query on the data base to pick one random Plate of a selected meal
     *
     * @param calories
     * @param meal
     * @return
     */
    private Plate selectPlate(int calories, String meal) {
        List<Plate> toSelect = pd.findAllByCalories(calories, meal);
        if (calories != 0) {
            if (!toSelect.isEmpty()) {
                return pickRandomPlate(toSelect);
            } else {
                return selectPlate(calories - 50, meal);
            }

        } else {
            return new Plate();
        }
    }

    /**
     * Calculates sum of calories from a diet
     *
     * @param diet
     * @return
     */
    private int getSumCalories(Map<String, Plate> diet) {
        return diet.get(BREAKFAST).getCalories() + diet.get(LUNCH).getCalories() + diet.get(SUPPER).getCalories() + diet.get(DINNER).getCalories();
    }

    /**
     * Generates a map with one plate per meal.
     *
     * @param requestedDiets
     * @return
     */
    public GeneratedDietDto generateDiet(Map<String, Integer> requestedDiets) {

        Map<String, Plate> diet = new HashMap<String, Plate>();
        diet.put(BREAKFAST, selectPlate(percentageToCalories(requestedDiets.get("calories"), requestedDiets.get("breakfastPerc")), BREAKFAST));
        diet.put(LUNCH, selectPlate(percentageToCalories(requestedDiets.get("calories"), requestedDiets.get("lunchPerc")), LUNCH));
        diet.put(SUPPER, selectPlate(percentageToCalories(requestedDiets.get("calories"), requestedDiets.get("supperPerc")), SUPPER));
        diet.put(DINNER, selectPlate(percentageToCalories(requestedDiets.get("calories"), requestedDiets.get("dinnerPerc")), DINNER));

        GeneratedDietDto dietRetrived = new GeneratedDietDto(getSumCalories(diet), diet.get(BREAKFAST).getName(), diet.get(LUNCH).getName(), diet.get(SUPPER).getName(), diet.get(DINNER).getName());
        return dietRetrived;
    }

}