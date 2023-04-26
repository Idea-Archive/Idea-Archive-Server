package Idea.Archive.IdeaArchive.domain.image.exception;

import Idea.Archive.IdeaArchive.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class NotExistImageException extends RuntimeException {

    private final ErrorCode errorCode;

    public NotExistImageException(String message) {
        super(message);
        this.errorCode = ErrorCode.NOT_EXIST_IMAGE_EXCEPTION;
    }
}
