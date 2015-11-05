package com.cesi.cops.exceptions;

import org.springframework.security.core.AuthenticationException;

/**
 * This exception is throw in case of a not activated user trying to authenticate.
 */
public class UserNotActiveException extends AuthenticationException {

    public UserNotActiveException(String message) {
        super(message);
    }

    public UserNotActiveException(String message, Throwable t) {
        super(message, t);
    }
}
