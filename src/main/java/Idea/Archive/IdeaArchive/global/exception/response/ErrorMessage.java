package Idea.Archive.IdeaArchive.global.exception.response;

import Idea.Archive.IdeaArchive.global.exception.enumType.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {

    private String message;
    private int status;

    public ErrorMessage(ErrorCode errorCode) {
        this.message = errorCode.getMessage();
        this.status = errorCode.getStatus();
    }

}
