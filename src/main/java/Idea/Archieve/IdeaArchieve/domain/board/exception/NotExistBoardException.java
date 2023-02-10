package Idea.Archieve.IdeaArchieve.domain.board.exception;

import Idea.Archieve.IdeaArchieve.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class NotExistBoardException extends RuntimeException {

    private final ErrorCode errorCode;

    public NotExistBoardException(String message) {
        super(message);
        this.errorCode = ErrorCode.NOT_EXIST_BOARD;
    }
}
