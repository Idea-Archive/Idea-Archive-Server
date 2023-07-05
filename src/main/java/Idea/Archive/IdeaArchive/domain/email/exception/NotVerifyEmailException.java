package Idea.Archive.IdeaArchive.domain.email.exception;

import Idea.Archive.IdeaArchive.global.exception.IdeaArchiveException;
import Idea.Archive.IdeaArchive.global.exception.enums.ErrorCode;

public class NotVerifyEmailException extends IdeaArchiveException {

    public NotVerifyEmailException() {
        super(ErrorCode.NOT_VERIFY_EMAIL);
    }
}
