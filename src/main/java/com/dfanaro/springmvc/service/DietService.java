package com.dfanaro.springmvc.service;

import com.dfanaro.springmvc.dao.DietDao;
import com.dfanaro.springmvc.dao.PlateDao;
import com.dfanaro.springmvc.dto.GeneratedDietDto;
import com.dfanaro.springmvc.dto.SavedDietDto;
import com.dfanaro.springmvc.entity.Diet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.apache.commons.lang3.Validate.notNull;

/**
 * Diet service that generate a random list of diets using Plate entity
 *
 * @author dfanaro
 */
@Service
public class DietService {

    private static final int DIETS_AMOUNT = 5;
    private DietDao dietDao;

    private DietGeneratorService dietGeneratorService;
    private PlateDao plateDao;

    /**
     * Dummy constructor used by Spring.
     */
    protected DietService() {
    }

    /**
     * "DietDao" and "PlateDao" injection to get access to the Data Base
     *
     * @param dietDao
     * @param plateDao
     */
    @Autowired
    public void DietService(DietDao dietDao, PlateDao plateDao, DietGeneratorService dietGen) {
        this.dietDao = notNull(dietDao, "The Diet DAO could not be null");
        this.plateDao = notNull(plateDao, "The plate DAO could not be null");
        this.dietGeneratorService = dietGen;
    }

    /**
     * Algorithm to generate the random diets
     *
     * @return savedDietDto
     */
    @Transactional
    public List<SavedDietDto> getDiets() {
        List<SavedDietDto> diets = new ArrayList<SavedDietDto>();
        List<Diet> dietEntities = dietDao.findAll();
        for (Diet diet : dietEntities) {
            diets.add(diet.toDto());
        }
        return diets;
    }

    /**
     * Calls the service that returns a generated diet
     *
     * @param requestedDiets
     * @return
     */
    @Transactional(readOnly = true)
    public List<GeneratedDietDto> suggestDiets(Map<String, Integer> requestedDiets) {

        List<GeneratedDietDto> diets = new ArrayList<GeneratedDietDto>();
        for (int index = 0; index < DIETS_AMOUNT; index++) {
            diets.add(dietGeneratorService.generateDiet(requestedDiets));
        }
        return diets;
    }

    /**
     * Save a diet in the data base
     *
     * @param diet
     */
    @Transactional
    public void saveDiet(Map<String, String> diet) {
        Diet dietEnt = new Diet();
        dietEnt.setName(diet.get("name"));
        dietEnt.setScore(0);
        dietEnt.setBreakfast(plateDao.findByName(diet.get("breakfast")));
        dietEnt.setLunch(plateDao.findByName(diet.get("lunch")));
        dietEnt.setDinner(plateDao.findByName(diet.get("dinner")));
        dietEnt.setSupper(plateDao.findByName(diet.get("supper")));
        dietDao.save(dietEnt);
    }

    /**
     * Method to return a specific diet by name
     *
     * @param id
     * @return savedDietDto
     */
    @Transactional
    public SavedDietDto getDiet(String id) {
        return dietDao.findByName(id).toDto();
    }

    /**
     * Method to modify a specific diet
     *
     * @param dietName
     * @param savedDietDto
     */
    @Transactional
    public void modifyDiet(String dietName, SavedDietDto savedDietDto) {
        Diet diet = dietDao.findByName(dietName);
        diet.setScore(savedDietDto.getScore());
        diet.setName(savedDietDto.getName());
        dietDao.save(diet);
    }

    /**
     * Method to delete a specific diet
     *
     * @param dietName
     */
    @Transactional
    public void deleteDiet(String dietName) {
        dietDao.remove(dietDao.findByName(dietName));
    }
}
