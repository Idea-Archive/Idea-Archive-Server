package Idea.Archive.IdeaArchive.domain.member.exception;

import Idea.Archive.IdeaArchive.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class AlreadyExistNicknameException extends RuntimeException{

    private final ErrorCode errorCode;

    public AlreadyExistNicknameException(String message){
        super(message);
        this.errorCode = ErrorCode.ALREADY_EXIST_ID;
    }
}
