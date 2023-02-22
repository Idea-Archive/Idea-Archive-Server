package Idea.Archieve.IdeaArchieve.domain.post.exception;

import Idea.Archieve.IdeaArchieve.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class AlreadyInsertHeartException extends RuntimeException{

    private final ErrorCode errorCode;

    public AlreadyInsertHeartException(String message){
        super(message);
        this.errorCode = ErrorCode.ALREADY_INSERT_HEART;
    }
}
