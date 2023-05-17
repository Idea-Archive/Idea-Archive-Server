package Idea.Archive.IdeaArchive.domain.email.exception;


import Idea.Archive.IdeaArchive.global.exception.IdeaArchiveException;
import Idea.Archive.IdeaArchive.global.exception.enumType.ErrorCode;
import lombok.Getter;

@Getter
public class FailedSendEmailException extends IdeaArchiveException {

    public FailedSendEmailException() {
        super(ErrorCode.FAILED_SEND_EMAIL);
    }
}
