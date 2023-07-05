package Idea.Archive.IdeaArchive.domain.post.exception;

import Idea.Archive.IdeaArchive.global.exception.IdeaArchiveException;
import Idea.Archive.IdeaArchive.global.exception.enums.ErrorCode;

public class NotExistPostException extends IdeaArchiveException {

    public NotExistPostException() {
        super(ErrorCode.NOT_EXIST_POST);
    }
}
