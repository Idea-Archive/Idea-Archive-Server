package Idea.Archieve.IdeaArchieve.domain.post.exception;

import Idea.Archieve.IdeaArchieve.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class NotMyPost extends RuntimeException {

    private final ErrorCode errorCode;

    public NotMyPost(String message) {
        super(message);
        this.errorCode = ErrorCode.NOT_MY_POST;
    }
}
