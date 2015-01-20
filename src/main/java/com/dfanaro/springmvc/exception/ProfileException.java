package com.dfanaro.springmvc.exception;

/**
 * Created with IntelliJ IDEA.
 * User: polak
 * Date: 5/28/13
 * Time: 12:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class ProfileException extends RuntimeException {

    public static final String PROFILE_NOT_FOUND = "The profile was not found";

    public ProfileException(String error) {
        super(error);
    }

}
