package Idea.Archive.IdeaArchive.domain.post.exception;

import Idea.Archive.IdeaArchive.global.exception.IdeaArchiveException;
import Idea.Archive.IdeaArchive.global.exception.enums.ErrorCode;

public class NotVerifyMember extends IdeaArchiveException {

    public NotVerifyMember() {
        super(ErrorCode.NOT_VERIFY_MEMBER);
    }
}
