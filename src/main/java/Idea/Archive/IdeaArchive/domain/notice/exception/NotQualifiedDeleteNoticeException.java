package Idea.Archive.IdeaArchive.domain.notice.exception;

import Idea.Archive.IdeaArchive.global.exception.IdeaArchiveException;
import Idea.Archive.IdeaArchive.global.exception.enums.ErrorCode;

public class NotQualifiedDeleteNoticeException extends IdeaArchiveException {

    public NotQualifiedDeleteNoticeException() {
        super(ErrorCode.NOT_QUALIFIED_DELETE_NOTICE);
    }
}
