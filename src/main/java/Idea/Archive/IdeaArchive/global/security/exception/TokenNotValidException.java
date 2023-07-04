package Idea.Archive.IdeaArchive.global.security.exception;

import Idea.Archive.IdeaArchive.global.exception.IdeaArchiveException;
import Idea.Archive.IdeaArchive.global.exception.enumType.ErrorCode;

public class TokenNotValidException extends IdeaArchiveException {

    public TokenNotValidException() {
        super(ErrorCode.TOKEN_NOT_VALID);
    }

}
