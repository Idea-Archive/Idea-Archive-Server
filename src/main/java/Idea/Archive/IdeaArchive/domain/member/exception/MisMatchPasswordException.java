package Idea.Archive.IdeaArchive.domain.member.exception;

import Idea.Archive.IdeaArchive.global.exception.IdeaArchiveException;
import Idea.Archive.IdeaArchive.global.exception.enums.ErrorCode;

public class MisMatchPasswordException extends IdeaArchiveException {

    public MisMatchPasswordException() {
        super(ErrorCode.MISMATCH_MEMBER_PASSWORD);
    }
}
