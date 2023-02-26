package Idea.Archieve.IdeaArchieve.infrastructure.feign.exception;

import Idea.Archieve.IdeaArchieve.global.exception.ErrorCode;

public class UnAuthorizedException extends RuntimeException {

    private final ErrorCode errorCode;

    public UnAuthorizedException(String message) {
        super(message);
        this.errorCode = ErrorCode.UNAUTHORIZED;
    }
}
