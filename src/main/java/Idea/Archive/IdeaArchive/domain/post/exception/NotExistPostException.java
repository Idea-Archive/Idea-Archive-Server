package Idea.Archive.IdeaArchive.domain.post.exception;

import Idea.Archive.IdeaArchive.global.exception.IdeaArchiveException;
import Idea.Archive.IdeaArchive.global.exception.enumType.ErrorCode;
import lombok.Getter;

@Getter
public class NotExistPostException extends IdeaArchiveException {

    public NotExistPostException() {
        super(ErrorCode.NOT_EXIST_POST);
    }
}
