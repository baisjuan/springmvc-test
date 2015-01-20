package com.devspark.dietsystem.dao;

import com.devspark.dietsystem.entity.Plate;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * This class models the Data Access Object for entity Plate
 *
 * @author fvega
 */
@Repository
public class PlateDao extends AbstractDao<Plate> {

    @Override
    protected Class<Plate> getPersistentClass() {
        return Plate.class;
    }

    /**
     * Find a Plate with the given name
     *
     * @param name Name of the Plate to find
     * @return Plate The entity that contains the given name
     */
    public Plate findByName(String name) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Plate> criteria = builder.createQuery(getPersistentClass());
        Root<Plate> entityRoot = criteria.from(getPersistentClass());
        Predicate condition = builder.equal(entityRoot.get("name"), name);
        criteria.where(condition);
        criteria.select(entityRoot);
        return entityManager.createQuery(criteria).getSingleResult();
    }

    public List<Plate> findAllByCalories(int Calories, String meal) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Plate> criteria = builder.createQuery(getPersistentClass());
        Root<Plate> entityRoot = criteria.from(getPersistentClass());
        Predicate conditionCalories = builder.and(
                builder.between(entityRoot.<Integer>get("calories"), (int) (Calories * 0.9f), Math.round(Calories * 1.1f)),
                builder.equal(entityRoot.get(meal.toLowerCase()), true));
        criteria.where(conditionCalories);
        criteria.select(entityRoot);

        return entityManager.createQuery(criteria).getResultList();
    }

}