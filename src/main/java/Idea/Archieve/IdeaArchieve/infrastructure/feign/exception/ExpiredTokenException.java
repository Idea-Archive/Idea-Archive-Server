package Idea.Archieve.IdeaArchieve.infrastructure.feign.exception;

import Idea.Archieve.IdeaArchieve.global.exception.ErrorCode;

public class ExpiredTokenException extends RuntimeException {
    private final ErrorCode errorCode;

    public ExpiredTokenException(String message) {
        super(message);
        this.errorCode = ErrorCode.EXPIRED_TOKEN;
    }
}
