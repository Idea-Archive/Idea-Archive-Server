package Idea.Archieve.IdeaArchieve.domain.email.exception;


import Idea.Archieve.IdeaArchieve.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class ManyRequestEmailException extends RuntimeException{

    private final ErrorCode errorCode;

    public ManyRequestEmailException(String message){
        super(message);
        this.errorCode = ErrorCode.MANY_REQUEST_EMAIL_AUTH;
    }
}
