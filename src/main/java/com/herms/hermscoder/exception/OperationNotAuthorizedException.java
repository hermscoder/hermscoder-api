package com.herms.hermscoder.exception;

import org.springframework.security.access.AccessDeniedException;

public class OperationNotAuthorizedException extends AccessDeniedException {
    public OperationNotAuthorizedException(String msg) {
        super(msg);
    }
}
