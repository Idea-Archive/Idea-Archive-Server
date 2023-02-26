package Idea.Archieve.IdeaArchieve.infrastructure.feign.exception;

import Idea.Archieve.IdeaArchieve.global.exception.ErrorCode;

public class BadRequestException extends RuntimeException {

    private final ErrorCode errorCode;

    public BadRequestException(String message) {
        super(message);
        this.errorCode = ErrorCode.BAD_REQUEST;
    }
}
