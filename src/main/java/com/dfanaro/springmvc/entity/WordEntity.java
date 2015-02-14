package com.dfanaro.springmvc.entity;

import com.dfanaro.springmvc.dto.DTORepresentation;
import com.dfanaro.springmvc.dto.WordDTO;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * TODO: brief comment about this class
 *
 * @author Damian Fanaro (damianfanaro@gmail.com)
 * @date 24/01/15
 */
@Entity(name = "word")
public class WordEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String definition;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    @Override
    protected Class<? extends BaseEntity> entityClass() {
        return WordEntity.class;
    }

    @Override
    public DTORepresentation toDTO() {
        return new WordDTO(name, definition);
    }
}
