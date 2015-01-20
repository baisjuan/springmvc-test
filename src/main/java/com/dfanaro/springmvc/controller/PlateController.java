package com.dfanaro.springmvc.controller;

import com.dfanaro.springmvc.dto.PlateDto;
import com.dfanaro.springmvc.service.PlateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Plate controller to map the incoming urls from HTTP
 *
 * @author dfanaro
 */
@Controller
public class PlateController {

    public final String ELEMENT_SAVED = "The element has been successfully saved to the database";
    public final String ELEMENT_MODIFIED = "The element has been successfully modified in the database";
    public final String ELEMENT_DELETED = "The element has been successfully deleted from the database";

    private PlateService plateService;

    @Autowired
    public PlateController(PlateService plateService) {
        this.plateService = plateService;
    }

    /**
     * Retrieve the full list of plates from the data base
     *
     * @return JSON object for Plate that contains a list of plates inside
     */
    @RequestMapping(value = "/food", method = RequestMethod.GET)
    @ResponseBody
    public List<PlateDto> getPlateList() {
        List<PlateDto> plateList = new ArrayList<>();
        plateList.addAll(plateService.getPlates());
        return plateList;
    }

    /**
     * Add a new plate into the data base
     *
     * @param plateDto
     * @return string message
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/food", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String addPlate(@RequestBody PlateDto plateDto) throws UnsupportedEncodingException {
        plateService.addPlate(plateDto);
        return ELEMENT_SAVED;
    }

    /**
     * Retrieve a plate with the given id parameter
     *
     * @param id
     * @return PlateDto
     */
    @RequestMapping(value = "/food/{id}", method = RequestMethod.GET)
    @ResponseBody
    public PlateDto getPlate(@PathVariable String id) {
        return plateService.getPlate(id);
    }

    /**
     * Modifies a plate with the given dto object for a specific id
     *
     * @param id
     * @param plateDto
     * @return string message
     */
    @RequestMapping(value = "/food/{id}", method = RequestMethod.PUT, consumes = "application/json")
    @ResponseBody
    public String modifyPlate(@PathVariable String id, @RequestBody PlateDto plateDto) {
        plateService.modifyPlate(id, plateDto);
        return ELEMENT_MODIFIED;
    }

    /**
     * Delete a plate with the given id parameter
     *
     * @param id
     * @return string message
     */
    @RequestMapping(value = "/food/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deletePlate(@PathVariable String id) {
        plateService.deletePlate(id);
        return ELEMENT_DELETED;
    }
}
