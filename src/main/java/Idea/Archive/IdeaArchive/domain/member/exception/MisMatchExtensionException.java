package Idea.Archive.IdeaArchive.domain.member.exception;

import Idea.Archive.IdeaArchive.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class MisMatchExtensionException extends RuntimeException {

    private final ErrorCode errorCode;

    public MisMatchExtensionException(String message) {
        super(message);
        this.errorCode = ErrorCode.MISMATCH_EXTENSION;
    }

}
