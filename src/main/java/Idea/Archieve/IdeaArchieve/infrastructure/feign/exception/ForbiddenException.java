package Idea.Archieve.IdeaArchieve.infrastructure.feign.exception;

import Idea.Archieve.IdeaArchieve.global.exception.ErrorCode;

public class ForbiddenException extends RuntimeException {

    private final ErrorCode errorCode;

    public ForbiddenException(String message) {
        super(message);
        this.errorCode = ErrorCode.FORBIDDEN;
    }
}
