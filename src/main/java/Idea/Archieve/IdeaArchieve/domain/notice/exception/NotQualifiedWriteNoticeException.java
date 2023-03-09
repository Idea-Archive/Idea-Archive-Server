package Idea.Archieve.IdeaArchieve.domain.notice.exception;

import Idea.Archive.IdeaArchive.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class NotQualifiedWriteNoticeException extends RuntimeException{
    private final ErrorCode errorCode;

    public NotQualifiedWriteNoticeException(String message){
        super(message);
        this.errorCode = ErrorCode.NOT_QUALIFIED_WRITE_NOTICE;
    }
}
