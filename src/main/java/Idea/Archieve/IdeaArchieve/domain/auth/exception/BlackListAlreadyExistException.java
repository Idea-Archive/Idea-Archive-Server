package Idea.Archieve.IdeaArchieve.domain.auth.exception;

import Idea.Archieve.IdeaArchieve.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BlackListAlreadyExistException extends RuntimeException{

    private final ErrorCode errorcode;

    public BlackListAlreadyExistException(String message){
        super(message);
        this.errorcode = ErrorCode.BLACKLIST_ALREADY_EXIST;
    }
}
