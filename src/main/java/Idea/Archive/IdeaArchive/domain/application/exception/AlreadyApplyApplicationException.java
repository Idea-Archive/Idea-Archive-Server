package Idea.Archive.IdeaArchive.domain.application.exception;

import Idea.Archive.IdeaArchive.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class AlreadyApplyApplicationException extends RuntimeException {
    private final ErrorCode errorCode;

    public AlreadyApplyApplicationException(String message) {
        super(message);
        this.errorCode = ErrorCode.ALREADY_APPLY_APPLICATION;
    }
}
