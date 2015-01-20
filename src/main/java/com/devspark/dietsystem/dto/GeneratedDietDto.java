package com.devspark.dietsystem.dto;

/**
 * This class models a DTO for a diet that is generated dynamically from the DB
 *
 * @autor abarragan
 */
public class GeneratedDietDto {

    int calories;
    private String breakfast;
    private String lunch;
    private String supper;
    private String dinner;

    public GeneratedDietDto(int calories, String breakfast, String lunch, String supper, String dinner) {
        this.calories = calories;
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.supper = supper;
        this.dinner = dinner;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(String breakfast) {
        this.breakfast = breakfast;
    }

    public String getLunch() {
        return lunch;
    }

    public void setLunch(String lunch) {
        this.lunch = lunch;
    }

    public String getSupper() {
        return supper;
    }

    public void setSupper(String supper) {
        this.supper = supper;
    }

    public String getDinner() {
        return dinner;
    }

    public void setDinner(String dinner) {
        this.dinner = dinner;
    }
}
