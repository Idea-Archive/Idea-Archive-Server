package Idea.Archive.IdeaArchive.domain.member.exception;


import Idea.Archive.IdeaArchive.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class MisMatchPasswordException extends RuntimeException{

    private final ErrorCode errorCode;

    public MisMatchPasswordException(String message){
        super(message);
        this.errorCode = ErrorCode.MISMATCH_MEMBER_PASSWORD;

    }
}
