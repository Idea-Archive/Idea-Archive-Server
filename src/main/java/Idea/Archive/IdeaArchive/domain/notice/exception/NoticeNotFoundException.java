package Idea.Archive.IdeaArchive.domain.notice.exception;

import Idea.Archive.IdeaArchive.global.exception.IdeaArchiveException;
import Idea.Archive.IdeaArchive.global.exception.enumType.ErrorCode;
import lombok.Getter;

@Getter
public class NoticeNotFoundException extends IdeaArchiveException {

    public NoticeNotFoundException() {
        super(ErrorCode.NOT_FOUND_NOTICE);
    }
}
