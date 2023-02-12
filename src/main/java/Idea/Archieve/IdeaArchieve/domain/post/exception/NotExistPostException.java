package Idea.Archieve.IdeaArchieve.domain.post.exception;

import Idea.Archieve.IdeaArchieve.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class NotExistPostException extends RuntimeException {

    private final ErrorCode errorCode;

    public NotExistPostException(String message) {
        super(message);
        this.errorCode = ErrorCode.NOT_EXIST_POST;
    }
}
