package Idea.Archieve.IdeaArchieve.domain.post.exception;

import Idea.Archieve.IdeaArchieve.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class NotVerifyMember extends RuntimeException {

    private final ErrorCode errorCode;

    public NotVerifyMember(String message) {
        super(message);
        this.errorCode = ErrorCode.NOT_VERIFY_MEMBER;
    }
}
