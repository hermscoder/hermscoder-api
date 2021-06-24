package com.herms.hermscoder.exception;

import org.springframework.security.core.AuthenticationException;

public class IncorrectUsernameOrPasswordException extends AuthenticationException {
    public IncorrectUsernameOrPasswordException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public IncorrectUsernameOrPasswordException(String msg) {
        super(msg);
    }
}
