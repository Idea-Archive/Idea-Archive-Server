package Idea.Archive.IdeaArchive.infrastructure.feign.exception;

import Idea.Archive.IdeaArchive.global.exception.enums.ErrorCode;

public class ExpiredTokenException extends RuntimeException {
    private final ErrorCode errorCode;

    public ExpiredTokenException(String message) {
        super(message);
        this.errorCode = ErrorCode.EXPIRED_TOKEN;
    }
}
