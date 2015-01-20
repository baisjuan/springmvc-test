package com.devspark.dietsystem.controller;

import com.devspark.dietsystem.dto.GeneratedDietDto;
import com.devspark.dietsystem.dto.SavedDietDto;
import com.devspark.dietsystem.service.DietService;
import com.devspark.dietsystem.util.AbstractList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * This controller maps urls related to diet manipulation
 *
 * @autor abarragan
 */
@Controller
public class DietController {

    public final String ELEMENT_SAVED = "The element has been successfully saved to the database";
    public final String ELEMENT_MODIFIED = "The element has been successfully modified in the database";
    public final String ELEMENT_DELETED = "The element has been successfully deleted from the database";

    private DietService dietService;

    @Autowired
    public DietController(DietService dietService) {
        this.dietService = dietService;
    }

    /**
     * Retrieve the entire list of diets from DB
     *
     * @return JSON object for Diet that contains a list of plates inside
     */
    @RequestMapping(value = "/diet", method = RequestMethod.GET)
    @ResponseBody
    public AbstractList<SavedDietDto> getDietList() {
        AbstractList<SavedDietDto> dietList = new AbstractList<SavedDietDto>();
        dietList.addList(dietService.getDiets());
        return dietList;
    }

    /**
     * Generate diets with default distribution values
     *
     * @return ModelAndView
     */
    @RequestMapping(value = "/diet/generate/{calories}", method = RequestMethod.GET)
    @ResponseBody
    public AbstractList<GeneratedDietDto> getDiets(@PathVariable String calories) {
        Map<String, Integer> requestedDiets = new HashMap<String, Integer>();
        requestedDiets.put("calories", Integer.parseInt(calories));
        requestedDiets.put("breakfastPerc", 15);
        requestedDiets.put("lunchPerc", 35);
        requestedDiets.put("supperPerc", 15);
        requestedDiets.put("dinnerPerc", 35);

        AbstractList<GeneratedDietDto> diets = new AbstractList<GeneratedDietDto>();
        diets.addList(dietService.suggestDiets(requestedDiets));
        return diets;
    }

    /**
     * Generate diets specifying distribution values
     *
     * @return ModelAndView
     */
    @RequestMapping(value = "/diet/generate", method = RequestMethod.POST)
    @ResponseBody
    public AbstractList<GeneratedDietDto> getDietsPercentage(@RequestBody Map<String, Integer> requestedDiets) {
        AbstractList<GeneratedDietDto> diets = new AbstractList<GeneratedDietDto>();
        diets.addList(dietService.suggestDiets(requestedDiets));
        return diets;
    }

    /**
     * Save a diet in the data base
     *
     * @param diet
     * @return string message
     */
    @RequestMapping(value = "/diet", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public String addDiet(@RequestBody Map<String, String> diet) {
        dietService.saveDiet(diet);
        return ELEMENT_SAVED;
    }

    /**
     * Retrieve a diet with the given id parameter
     *
     * @param id
     * @return string message
     */
    @RequestMapping(value = "/diet/{id}", method = RequestMethod.GET)
    @ResponseBody
    public SavedDietDto getDiet(@PathVariable String id) {
        return dietService.getDiet(id);
    }

    /**
     * Modifies a diet with the given dto object for a specific id
     *
     * @param savedDietDto
     * @return string message
     */
    @RequestMapping(value = "/diet/{id}", method = RequestMethod.PUT, consumes = "application/json")
    @ResponseBody
    public String modifyDiet(@PathVariable String id, @RequestBody SavedDietDto savedDietDto) {
        dietService.modifyDiet(id, savedDietDto);
        return ELEMENT_MODIFIED;
    }

    /**
     * Delete a diet with the given id parameter
     *
     * @param id
     * @return string message
     */
    @RequestMapping(value = "/diet/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteDiet(@PathVariable String id) {
        dietService.deleteDiet(id);
        return ELEMENT_DELETED;
    }
}
