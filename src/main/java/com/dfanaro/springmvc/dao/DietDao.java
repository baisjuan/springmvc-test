package com.dfanaro.springmvc.dao;

import com.dfanaro.springmvc.entity.Diet;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * This class models the Data Access Object for entity Diet
 *
 * @author dfanaro
 */
@Repository
public class DietDao extends AbstractDao<Diet> {

    @Override
    protected Class<Diet> getPersistentClass() {
        return Diet.class;
    }

    /**
     * Find a Diet with the given name
     *
     * @param name Name of the Diet to find
     * @return Diet The entity that contains the given name
     */
    public Diet findByName(String name) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Diet> criteria = builder.createQuery(getPersistentClass());
        Root<Diet> entityRoot = criteria.from(getPersistentClass());
        Predicate condition = builder.equal(entityRoot.get("name"), name);
        criteria.where(condition);
        criteria.select(entityRoot);
        return entityManager.createQuery(criteria).getSingleResult();
    }
}
