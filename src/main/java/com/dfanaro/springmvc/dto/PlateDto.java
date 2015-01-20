package com.dfanaro.springmvc.dto;

import com.dfanaro.springmvc.entity.Plate;
import org.apache.solr.client.solrj.beans.Field;

/**
 * This class models the Data Transfer Object for entity Plate
 *
 * @author dfanaro
 */
public class PlateDto {

    @Field
    private String name;
    @Field
    private int calories;
    @Field
    private boolean lunch;
    @Field
    private boolean dinner;
    @Field
    private boolean breakfast;
    @Field
    private boolean supper;

    public PlateDto() {

    }

    public PlateDto(String name, int calories, boolean lunch, boolean dinner, boolean breakfast, boolean supper) {
        this.name = name;
        this.calories = calories;
        this.lunch = lunch;
        this.dinner = dinner;
        this.breakfast = breakfast;
        this.supper = supper;
    }

    public boolean isLunch() {
        return lunch;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public boolean isDinner() {
        return dinner;
    }

    public boolean isBreakfast() {
        return breakfast;
    }

    public PlateDto(String name) {

        this.name = name;

    }

    public boolean isSupper() {
        return supper;
    }


    public int getCalories() {
        return calories;
    }

    public void setLunch(boolean lunch) {
        this.lunch = lunch;
    }

    public void setDinner(boolean dinner) {
        this.dinner = dinner;
    }

    public void setBreakfast(boolean breakfast) {
        this.breakfast = breakfast;
    }

    public void setSupper(boolean supper) {
        this.supper = supper;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append(name);
        buf.append(",");
        buf.append(calories);
        buf.append(",");
        buf.append(breakfast);
        buf.append(",");
        buf.append(lunch);
        buf.append(",");
        buf.append(supper);
        buf.append(",");
        buf.append(dinner);
        return buf.toString();
    }

    /**
     * Creates a {@link Plate} based on the DTO information
     *
     * @return
     */
    public Plate createPlate() {
        Plate plate = new Plate();
        plate.setName(name);
        plate.setCalories(calories);
        plate.setBreakfast(breakfast);
        plate.setLunch(lunch);
        plate.setSupper(supper);
        plate.setDinner(dinner);
        return plate;
    }
}