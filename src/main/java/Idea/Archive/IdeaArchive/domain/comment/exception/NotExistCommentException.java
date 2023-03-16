package Idea.Archive.IdeaArchive.domain.comment.exception;

import Idea.Archive.IdeaArchive.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class NotExistCommentException extends RuntimeException {

    private final ErrorCode errorCode;

    public NotExistCommentException(String message) {
        super(message);
        this.errorCode = ErrorCode.NOT_EXIST_COMMENT;
    }
}
