package com.dfanaro.springmvc.dto;

/**
 * TODO: brief comment about this class
 *
 * @author Damian Fanaro (damianfanaro@gmail.com)
 * @date 24/01/15
 */
public class WordDTO implements DTORepresentation {

    private String name;
    private String definition;

    public WordDTO(String name, String definition) {
        this.name = name;
        this.definition = definition;
    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WordDTO wordDTO = (WordDTO) o;

        return name.equals(wordDTO.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
