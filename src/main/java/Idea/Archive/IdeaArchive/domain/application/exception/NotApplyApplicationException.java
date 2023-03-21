package Idea.Archive.IdeaArchive.domain.application.exception;

import Idea.Archive.IdeaArchive.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class NotApplyApplicationException extends RuntimeException{
    private final ErrorCode errorCode;

    public NotApplyApplicationException(String message) {
        super(message);
        this.errorCode = ErrorCode.NOT_APPLY_APPLICATION;
    }
}
