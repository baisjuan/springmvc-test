package com.devspark.dietsystem.dao;

import com.devspark.dietsystem.entity.BaseEntity;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public abstract class AbstractDao<T extends BaseEntity> {

    protected EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    protected abstract Class<T> getPersistentClass();

    /**
     * Saves a new entity or updates an existing entity to the database.
     *
     * @param entity The entity to save. It cannot be null.
     */
    public void save(final T entity) {
        Validate.notNull(entity, "The entity cannot be null");
        entityManager.persist(entity);
    }

    /**
     * Finds the entity with the specified id.
     *
     * @param id The id of the entity to search for.
     * @return Returns the entity with the specified id, or null if no such entity exists.
     */
    public T find(Long id) {
        Validate.notNull(id, "The entity ID cannot be null");
        T entity = entityManager.find(getPersistentClass(), id);
        return entity;
    }

    /**
     * Removes the specified entity from the database.
     *
     * @param entity The entity to remove. It cannot be null.
     */
    public void remove(final T entity) {
        Validate.notNull(entity, "The entity cannot be null");
        entityManager.remove(entity);
    }

    /**
     * Removes the specified entity from the database.
     *
     * @param id The entity id to remove. It cannot be null.
     */
    public void remove(Long id) {
        remove(find(id));
    }

    protected final EntityManager getEntityManager() {
        return entityManager;
    }

    public T merge(final T entity) {
        Validate.notNull(entity, "The entity cannot be null");
        return entityManager.merge(entity);
    }

    public void refresh(final T entity) {
        Validate.notNull(entity, "The entity cannot be null");
        entityManager.refresh(entity);
    }

    public void flush() {
        entityManager.flush();
    }

    public void clear() {
        entityManager.clear();
    }

    protected Predicate addSearchCondition(Predicate condition, CriteriaBuilder criteriaBuilder, Root<T> entity,
                                           String column, String keyword) {
        Expression<String> col = entity.get(column).as(String.class);
        Predicate likeCondition = criteriaBuilder.or(criteriaBuilder.like(col, "%" + keyword), criteriaBuilder.like(col,
                keyword + "%"));
        if (condition == null) {
            condition = likeCondition;
        } else {
            condition = criteriaBuilder.or(likeCondition, condition);
        }
        return condition;
    }

    protected <N> Predicate addEqCondition(Predicate condition, Expression<N> column, N value,
                                           CriteriaBuilder criteriaBuilder) {
        if (value != null) {
            Predicate conditionValue = criteriaBuilder.equal(column, value);
            if (condition == null) {
                condition = conditionValue;
            } else {
                condition = criteriaBuilder.and(condition, conditionValue);
            }
        }
        return condition;

    }

    protected <N extends Number> Predicate addGeCondition(Predicate condition, Expression<N> column, N value,
                                                          CriteriaBuilder criteriaBuilder) {
        Predicate conditionValue = criteriaBuilder.isNotNull(column);
        conditionValue = criteriaBuilder.and(conditionValue, criteriaBuilder.ge(column, value));
        if (condition == null) {
            condition = conditionValue;
        } else {
            condition = criteriaBuilder.and(condition, conditionValue);
        }
        return condition;
    }

    /**
     * Returns a list with all the instances of the specific entity
     *
     * @return List<T> The list of rows of the entity
     */
    public List<T> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(getPersistentClass());
        Root<T> entityRoot = criteria.from(getPersistentClass());
        criteria.select(entityRoot);
        return entityManager.createQuery(criteria).getResultList();
    }
}

