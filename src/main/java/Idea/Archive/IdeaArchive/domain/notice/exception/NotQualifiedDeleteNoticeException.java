package Idea.Archive.IdeaArchive.domain.notice.exception;

import Idea.Archive.IdeaArchive.global.exception.IdeaArchiveException;
import Idea.Archive.IdeaArchive.global.exception.enumType.ErrorCode;

public class NotQualifiedDeleteNoticeException extends IdeaArchiveException {

    public NotQualifiedDeleteNoticeException() {
        super(ErrorCode.NOT_QUALIFIED_DELETE_NOTICE);
    }
}
