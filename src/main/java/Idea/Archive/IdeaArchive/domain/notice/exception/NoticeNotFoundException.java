package Idea.Archive.IdeaArchive.domain.notice.exception;

import Idea.Archive.IdeaArchive.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class NoticeNotFoundException extends RuntimeException {

    private final ErrorCode errorCode;

    public NoticeNotFoundException(String message) {
        super(message);
        this.errorCode = ErrorCode.NOT_FOUND_NOTICE;
    }
}
