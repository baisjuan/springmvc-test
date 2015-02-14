package com.dfanaro.springmvc.exception;

/**
 * TODO: brief comment about this class
 *
 * @author Damian Fanaro (damianfanaro@gmail.com)
 * @date 24/01/15
 */
public class WordNotFoundException extends RuntimeException {

    public static final String WORD_NOT_FOUND = "The word was not found (%1$s)";

    public WordNotFoundException(String wordName) {
        super(String.format(WORD_NOT_FOUND, wordName));
    }

}
