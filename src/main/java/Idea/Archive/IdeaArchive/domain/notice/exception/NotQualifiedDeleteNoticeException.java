package Idea.Archive.IdeaArchive.domain.notice.exception;

import Idea.Archive.IdeaArchive.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class NotQualifiedDeleteNoticeException extends RuntimeException {

    private final ErrorCode errorCode;

    public NotQualifiedDeleteNoticeException(String message) {
        super(message);
        this.errorCode = ErrorCode.NOT_QUALIFIED_DELETE_NOTICE;
    }
}
