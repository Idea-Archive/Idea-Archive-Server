package Idea.Archive.IdeaArchive.domain.auth.exception;


import Idea.Archive.IdeaArchive.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class RefreshTokenNotFoundException extends RuntimeException {

    private final ErrorCode errorCode;

    public RefreshTokenNotFoundException(String message){
        this.errorCode = ErrorCode.REFRESH_TOKEN_NOT_FOUND;
    }
}
