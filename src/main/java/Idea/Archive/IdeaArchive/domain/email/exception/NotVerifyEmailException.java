package Idea.Archive.IdeaArchive.domain.email.exception;

import Idea.Archive.IdeaArchive.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class NotVerifyEmailException extends RuntimeException {

    private final ErrorCode errorCode;

    public NotVerifyEmailException(String message) {
        super(message);
        this.errorCode = ErrorCode.NOT_VERIFY_EMAIL;
    }
}
