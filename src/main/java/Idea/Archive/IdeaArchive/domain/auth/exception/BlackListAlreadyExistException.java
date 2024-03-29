package Idea.Archive.IdeaArchive.domain.auth.exception;

import Idea.Archive.IdeaArchive.global.exception.IdeaArchiveException;
import Idea.Archive.IdeaArchive.global.exception.enums.ErrorCode;

public class BlackListAlreadyExistException extends IdeaArchiveException {

    public BlackListAlreadyExistException() {
        super(ErrorCode.BLACKLIST_ALREADY_EXIST);
    }
}
