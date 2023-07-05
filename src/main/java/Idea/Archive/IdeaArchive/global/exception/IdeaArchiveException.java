package Idea.Archive.IdeaArchive.global.exception;

import Idea.Archive.IdeaArchive.global.exception.enums.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class IdeaArchiveException extends RuntimeException {

    private ErrorCode errorCode;
}
