package Idea.Archive.IdeaArchive.domain.email.exception;

import Idea.Archive.IdeaArchive.global.exception.IdeaArchiveException;
import Idea.Archive.IdeaArchive.global.exception.enums.ErrorCode;

public class MisMatchAuthCodeException extends IdeaArchiveException {

    public MisMatchAuthCodeException() {
        super(ErrorCode.MISMATCH_AUTH_CODE);
    }
}
