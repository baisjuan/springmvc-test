package com.dfanaro.springmvc.entity;

import com.dfanaro.springmvc.dto.DTORepresentation;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * TODO: brief comment about this class
 *
 * @author Damian Fanaro (damianfanaro@gmail.com)
 * @date 24/01/15
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = -8054184286041932202L;

    @Id
    @Column(name = "id", insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    public BaseEntity() {
        id = null;
    }

    public BaseEntity(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Subclasses must provide their Class definition through this method.
     * <code>equals</code> and <code>hashCode</code> implementations rely on this class.
     *
     * @return the entity class.
     */
    protected abstract Class<? extends BaseEntity> entityClass();

    /**
     * All entities must expose their DTO representations
     *
     * @return the DTO representation
     */
    public abstract DTORepresentation toDTO();

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) return true;

        if (getId() != null && entityClass().isInstance(obj)) {
            BaseEntity other = (BaseEntity) obj;
            return getId().equals(other.getId()) && entityClass().equals(other.entityClass());
        }

        return false;
    }

    @Override
    public final int hashCode() {
        if (getId() != null) {
            return entityClass().hashCode() + 7 * getId().hashCode();
        }
        return super.hashCode();
    }

    @Override
    public String toString() {
        return entityClass().getSimpleName() + "(id:" + getId() + ")";
    }

}
