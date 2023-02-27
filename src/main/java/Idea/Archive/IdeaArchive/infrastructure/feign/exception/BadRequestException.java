package Idea.Archive.IdeaArchive.infrastructure.feign.exception;

import Idea.Archive.IdeaArchive.global.exception.ErrorCode;

public class BadRequestException extends RuntimeException {

    private final ErrorCode errorCode;

    public BadRequestException(String message) {
        super(message);
        this.errorCode = ErrorCode.BAD_REQUEST;
    }
}
