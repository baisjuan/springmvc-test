package com.dfanaro.springmvc.controller;

import com.dfanaro.springmvc.dto.DTORepresentation;
import com.dfanaro.springmvc.service.WordServiceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * TODO: brief comment about this class
 *
 * @author Damian Fanaro (damianfanaro@gmail.com)
 * @date 24/01/15
 */
@RestController
public class WordController {

    WordServiceManager wordService;

    @Autowired
    public WordController(WordServiceManager wordService) {
        this.wordService = wordService;
    }

    @RequestMapping(value = "/word", method = RequestMethod.GET)
    @ResponseBody
    public List<DTORepresentation> getAllWordDefinitions() {
        return wordService.getAllDefinitions();
    }

    @RequestMapping(value = "/word/{name}", method = RequestMethod.GET)
    @ResponseBody
    public DTORepresentation getWordDefinition(@PathVariable String name) {
        return wordService.getDefinition(name);
    }

    @RequestMapping(value = "/word/{name}", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void saveNewWordDefinition(@PathVariable String name, @RequestBody String definition) {
        wordService.saveDefinition(name, definition);
    }

    @RequestMapping(value = "/word/{name}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void removeWordDefinition(@PathVariable String name) {
        wordService.removeDefinition(name);
    }

}
