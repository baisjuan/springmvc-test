package com.dfanaro.springmvc.dao;

import com.dfanaro.springmvc.entity.WordEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * TODO: brief comment about this class
 *
 * @author Damian Fanaro (damianfanaro@gmail.com)
 * @date 24/01/15
 */
@Repository
public class WordDAO extends AbstractDAO<WordEntity> {

    @Override
    protected Class<WordEntity> getPersistentClass() {
        return WordEntity.class;
    }

    /**
     * Find a word with the given name
     *
     * @param name Name of the Word to find
     * @return WordEntity The entity that contains the given name
     */
    public WordEntity findByName(String name) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<WordEntity> criteria = builder.createQuery(getPersistentClass());
        Root<WordEntity> entityRoot = criteria.from(getPersistentClass());
        Predicate condition = builder.equal(entityRoot.get("name"), name);
        criteria.where(condition);
        criteria.select(entityRoot);
        return entityManager.createQuery(criteria).getSingleResult();
    }

}
