package com.dfanaro.springmvc.entity;

import com.dfanaro.springmvc.dto.SavedDietDto;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Entity to persist a diet configuration in the data base
 *
 * @author dfanaro
 */
@Entity
public class Diet extends BaseEntity {

    private double score;

    @ManyToOne
    private Plate breakfast;

    @ManyToOne
    private Plate lunch;

    @ManyToOne
    private Plate supper;

    @ManyToOne
    private Plate dinner;

    private String name;

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Plate getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(Plate breakfast) {
        this.breakfast = breakfast;
    }

    public Plate getLunch() {
        return lunch;
    }

    public void setLunch(Plate lunch) {
        this.lunch = lunch;
    }

    public Plate getSupper() {
        return supper;
    }

    public void setSupper(Plate supper) {
        this.supper = supper;
    }

    public Plate getDinner() {
        return dinner;
    }

    public void setDinner(Plate dinner) {
        this.dinner = dinner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected Class<Diet> entityClass() {
        return Diet.class;
    }

    public SavedDietDto toDto() {
        return new SavedDietDto(score, breakfast.toDto(), lunch.toDto(), supper.toDto(), dinner.toDto(), name);
    }
}
