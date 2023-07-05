package Idea.Archive.IdeaArchive.domain.auth.exception;

import Idea.Archive.IdeaArchive.global.exception.IdeaArchiveException;
import Idea.Archive.IdeaArchive.global.exception.enums.ErrorCode;

public class ExistEmailException extends IdeaArchiveException {

    public ExistEmailException() {
        super(ErrorCode.EXPIRE_EMAIL_CODE);
    }
}
