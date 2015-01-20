package com.dfanaro.springmvc.service;

import com.dfanaro.springmvc.dao.PlateDao;
import com.dfanaro.springmvc.dto.PlateDto;
import com.dfanaro.springmvc.entity.Plate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @author fvega@devspark.com
 */

@Service
public class PlateService {

    private PlateDao plateDao;

    public PlateService(){

    }

    /**
     * Dummy constructor used by Spring.
     */
    @Autowired
    public PlateService(PlateDao plateDao) {
        this.plateDao = plateDao;
    }

    /**
     * Save a plate  in the data base
     * @param plateDto
     */
    @Transactional
    public void addPlate(PlateDto plateDto) throws UnsupportedEncodingException {
        Plate plate = new Plate();
        plate.setName(URLDecoder.decode(plateDto.getName(), "utf-8"));
        plate.setCalories(plateDto.getCalories());
        plate.setBreakfast(plateDto.isBreakfast());
        plate.setDinner(plateDto.isDinner());
        plate.setLunch(plateDto.isLunch());
        plate.setSupper(plateDto.isSupper());
        plateDao.save(plate);
    }

    /**
     *
     * @return
     */
    @Transactional
    public List<PlateDto> getPlates() {
        List<PlateDto> plates = new ArrayList<PlateDto>();
        List<Plate> plateEntities = plateDao.findAll();
        for (Plate plate : plateEntities) {
            plates.add(plate.toDto());
        }
        return plates;
    }

    /**
     * Method to return a specific plate by name
     *
     * @param plateName
     * @return plateDto
     */
    @Transactional
    public PlateDto getPlate(String plateName) {
        return plateDao.findByName(plateName).toDto();
    }

    /**
     * Method to modify a specific plate
     *
     * @param plateName
     * @param plateDto
     */
    @Transactional
    public void modifyPlate(String plateName, PlateDto plateDto) {
        Plate plate = plateDao.findByName(plateName);
        plate.setCalories(plateDto.getCalories());
        plate.setName(plateDto.getName());
        plate.setBreakfast(plateDto.isBreakfast());
        plate.setDinner(plateDto.isDinner());
        plate.setLunch(plateDto.isLunch());
        plate.setSupper(plateDto.isSupper());
        plateDao.save(plate);
    }

    /**
     * Method to delete a specific plate
     *
     * @param plateName
     */
    @Transactional
    public void deletePlate(String plateName) {
        plateDao.remove(plateDao.findByName(plateName));
    }
}
