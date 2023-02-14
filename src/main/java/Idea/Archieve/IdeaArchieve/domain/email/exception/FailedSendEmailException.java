package Idea.Archieve.IdeaArchieve.domain.email.exception;


import Idea.Archieve.IdeaArchieve.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class FailedSendEmailException extends RuntimeException{

    private final ErrorCode errorCode;

    public FailedSendEmailException(String message){
        super(message);
        this.errorCode = ErrorCode.FAILED_SEND_EMAIL;
    }
}
