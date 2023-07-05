package Idea.Archive.IdeaArchive.domain.image.exception;

import Idea.Archive.IdeaArchive.global.exception.IdeaArchiveException;
import Idea.Archive.IdeaArchive.global.exception.enums.ErrorCode;

public class NotExistImageException extends IdeaArchiveException {

    public NotExistImageException() {
        super(ErrorCode.NOT_EXIST_IMAGE_EXCEPTION);
    }
}
