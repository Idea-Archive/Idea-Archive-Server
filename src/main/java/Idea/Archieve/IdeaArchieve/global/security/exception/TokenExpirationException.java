package Idea.Archieve.IdeaArchieve.global.security.exception;

import Idea.Archieve.IdeaArchieve.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class TokenExpirationException extends RuntimeException {

    private final ErrorCode errorCode;

    public TokenExpirationException(String message) {
        super(message);
        this.errorCode = ErrorCode.TOKEN_EXPIRATION;
    }
}
