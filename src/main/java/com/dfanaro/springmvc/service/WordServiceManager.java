package com.dfanaro.springmvc.service;

import com.dfanaro.springmvc.dao.WordDAO;
import com.dfanaro.springmvc.dto.DTORepresentation;
import com.dfanaro.springmvc.entity.WordEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: brief comment about this class
 *
 * @author Damian Fanaro (damianfanaro@gmail.com)
 * @date 24/01/15
 */
@Service
public class WordServiceManager {

    WordDAO wordDAO;

    @Autowired
    public WordServiceManager(WordDAO wordDAO) {
        this.wordDAO = wordDAO;
    }

    public List<DTORepresentation> getAllDefinitions() {

        List<WordEntity> wordEntities = wordDAO.findAll();

        List<DTORepresentation> toReturn = new ArrayList<>();
        for (WordEntity word : wordEntities) {
            toReturn.add(word.toDTO());
        }

        return toReturn;
    }

    public DTORepresentation getDefinition(String name) {
        return wordDAO.findByName(name).toDTO();
    }

    public void saveDefinition(String name, String definition) {

    }

    public void removeDefinition(String name) {

    }
}
