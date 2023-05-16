package Idea.Archive.IdeaArchive.domain.email.exception;

import Idea.Archive.IdeaArchive.global.exception.IdeaArchiveException;
import Idea.Archive.IdeaArchive.global.exception.enumType.ErrorCode;
import lombok.Getter;

@Getter
public class NotVerifyEmailException extends IdeaArchiveException {

    public NotVerifyEmailException() {
        super(ErrorCode.NOT_VERIFY_EMAIL);
    }
}
