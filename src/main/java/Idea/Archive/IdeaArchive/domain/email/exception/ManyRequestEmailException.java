package Idea.Archive.IdeaArchive.domain.email.exception;


import Idea.Archive.IdeaArchive.global.exception.IdeaArchiveException;
import Idea.Archive.IdeaArchive.global.exception.enumType.ErrorCode;
import lombok.Getter;

@Getter
public class ManyRequestEmailException extends IdeaArchiveException {

    public ManyRequestEmailException() {
        super(ErrorCode.MANY_REQUEST_EMAIL_AUTH);
    }
}
