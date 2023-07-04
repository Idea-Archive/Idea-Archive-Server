package Idea.Archive.IdeaArchive.global.security.exception;

import Idea.Archive.IdeaArchive.global.exception.IdeaArchiveException;
import Idea.Archive.IdeaArchive.global.exception.enumType.ErrorCode;

public class TokenExpirationException extends IdeaArchiveException {

    public TokenExpirationException() {
        super(ErrorCode.TOKEN_EXPIRATION);
    }
}
