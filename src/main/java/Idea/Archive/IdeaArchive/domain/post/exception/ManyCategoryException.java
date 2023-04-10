package Idea.Archive.IdeaArchive.domain.post.exception;

import Idea.Archive.IdeaArchive.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class ManyCategoryException extends RuntimeException {

    private final ErrorCode errorCode;

    public ManyCategoryException(String message) {
        super(message);
        this.errorCode = ErrorCode.MANY_CATEGORY_EXCEPTION;
    }
}
