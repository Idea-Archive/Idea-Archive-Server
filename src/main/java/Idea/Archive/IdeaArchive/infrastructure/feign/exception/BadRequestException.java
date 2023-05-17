package Idea.Archive.IdeaArchive.infrastructure.feign.exception;

import Idea.Archive.IdeaArchive.global.exception.enumType.ErrorCode;

public class BadRequestException extends RuntimeException {

    private final ErrorCode errorCode;

    public BadRequestException(String message) {
        super(message);
        this.errorCode = ErrorCode.BAD_REQUEST;
    }
}
