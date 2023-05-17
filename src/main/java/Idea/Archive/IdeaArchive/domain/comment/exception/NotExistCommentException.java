package Idea.Archive.IdeaArchive.domain.comment.exception;

import Idea.Archive.IdeaArchive.global.exception.IdeaArchiveException;
import Idea.Archive.IdeaArchive.global.exception.enumType.ErrorCode;
import lombok.Getter;

@Getter
public class NotExistCommentException extends IdeaArchiveException {

    public NotExistCommentException() {
        super(ErrorCode.NOT_EXIST_COMMENT);
    }
}
