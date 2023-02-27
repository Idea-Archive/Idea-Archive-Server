package Idea.Archive.IdeaArchive.infrastructure.feign.exception;

import Idea.Archive.IdeaArchive.global.exception.ErrorCode;

public class ForbiddenException extends RuntimeException {

    private final ErrorCode errorCode;

    public ForbiddenException(String message) {
        super(message);
        this.errorCode = ErrorCode.FORBIDDEN;
    }
}
