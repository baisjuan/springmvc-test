package com.devspark.dietsystem.dto;

/**
 * This class models the Data Transfer Object for entity Diet
 *
 * @author dfanaro
 */
public class SavedDietDto {

    private double score;
    private PlateDto breakfast;
    private PlateDto lunch;
    private PlateDto supper;
    private PlateDto dinner;
    private String name;

    public SavedDietDto() {

    }

    public SavedDietDto(double score, PlateDto breakfastDto, PlateDto lunchDto, PlateDto meriendaDto, PlateDto dinnerDto,
                        String name) {
        this.score = score;
        this.breakfast = breakfastDto;
        this.lunch = lunchDto;
        this.supper = meriendaDto;
        this.dinner = dinnerDto;
        this.name = name;
    }

    public PlateDto getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(PlateDto breakfast) {
        this.breakfast = breakfast;
    }

    public PlateDto getLunch() {
        return lunch;
    }

    public void setLunch(PlateDto lunch) {
        this.lunch = lunch;
    }

    public PlateDto getSupper() {
        return supper;
    }

    public void setSupper(PlateDto supper) {
        this.supper = supper;
    }

    public PlateDto getDinner() {
        return dinner;
    }

    public void setDinner(PlateDto dinner) {
        this.dinner = dinner;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
