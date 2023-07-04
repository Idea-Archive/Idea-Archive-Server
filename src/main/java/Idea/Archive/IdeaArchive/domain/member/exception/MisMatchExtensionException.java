package Idea.Archive.IdeaArchive.domain.member.exception;

import Idea.Archive.IdeaArchive.global.exception.IdeaArchiveException;
import Idea.Archive.IdeaArchive.global.exception.enumType.ErrorCode;

public class MisMatchExtensionException extends IdeaArchiveException {

    public MisMatchExtensionException() {
        super(ErrorCode.MISMATCH_EXTENSION);
    }

}
