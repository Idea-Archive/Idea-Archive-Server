package Idea.Archive.IdeaArchive.domain.notice.exception;

import Idea.Archive.IdeaArchive.global.exception.IdeaArchiveException;
import Idea.Archive.IdeaArchive.global.exception.enums.ErrorCode;

public class NotQualifiedWriteNoticeException extends IdeaArchiveException {

    public NotQualifiedWriteNoticeException() {
        super(ErrorCode.NOT_QUALIFIED_WRITE_NOTICE);
    }
}
