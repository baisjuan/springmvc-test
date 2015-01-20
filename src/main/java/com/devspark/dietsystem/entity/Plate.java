package com.devspark.dietsystem.entity;

import com.devspark.dietsystem.dto.PlateDto;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Entity to persist a plate  in the data base.
 *
 * @author fvega@devspark.com
 */


@Entity
public class Plate extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private int calories;

    private boolean lunch;
    private boolean dinner;
    private boolean breakfast;
    private boolean supper;

    public boolean isLunch() {
        return lunch;
    }

    public boolean isSupper() {
        return supper;
    }

    public void setSupper(boolean supper) {
        this.supper = supper;
    }

    public boolean isDinner() {
        return dinner;
    }

    public boolean isBreakfast() {
        return breakfast;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getCalories() {
        return calories;
    }

    @Override
    protected Class<Plate> entityClass() {
        return Plate.class;
    }

    /**
     * Return the DTO representation.
     *
     * @return a {@link PlateDto} instance.
     */
    public PlateDto toDto() {
        return new PlateDto(name, calories, lunch, dinner, breakfast, supper);
    }

}