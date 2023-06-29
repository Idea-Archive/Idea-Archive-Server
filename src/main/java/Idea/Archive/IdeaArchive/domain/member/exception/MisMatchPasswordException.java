package Idea.Archive.IdeaArchive.domain.member.exception;

import Idea.Archive.IdeaArchive.global.exception.IdeaArchiveException;
import Idea.Archive.IdeaArchive.global.exception.enumType.ErrorCode;
import lombok.Getter;

@Getter
public class MisMatchPasswordException extends IdeaArchiveException {

    public MisMatchPasswordException() {
        super(ErrorCode.MISMATCH_MEMBER_PASSWORD);
    }
}
