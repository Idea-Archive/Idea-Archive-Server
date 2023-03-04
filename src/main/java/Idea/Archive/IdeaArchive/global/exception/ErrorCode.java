package Idea.Archive.IdeaArchive.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    NOT_EXIST_POST("존재하지 않는 게시글입니다.", 404),
    NOT_VERIFY_MEMBER("검증되지 않은 회원입니다.", 401),
    MANY_REQUEST_EMAIL_AUTH("15분에 최대 3번의 이메일 요청만 가능합니다." , 429),
    EXPIRE_EMAIL_CODE("이메일 인증번호 시간이 만료되었습니다.", 401),
    MEMBER_NOT_FOUND("존재하지 않는 회원입니다.", 404),
    ALREADY_EXIST_MEMBER("이미 존재하는 닉네임입니다",400),
    MISMATCH_AUTH_CODE("인증번호가 일치하지 않습니다." , 400),
    NOT_VERIFY_EMAIL("검증되지 않은 이메일입니다." , 401),
    MISMATCH_MEMBER_PASSWORD("비밀번호가 일치하지 않습니다.", 400),
    ALREADY_EXIST_EMAIL("이미 존재하는 이메일입니다." , 400),
    ALREADY_EXIST_ID("이미 존재하는 아이디입니다.", 400),
    TOKEN_NOT_VALID("토큰이 유효 하지 않습니다.", 401),
    TOKEN_EXPIRATION("토큰이 만료 되었습니다.", 401),
    REFRESH_TOKEN_NOT_FOUND("존재하지 않는 리프레시 토큰입니다.", 404),
    FAILED_SEND_EMAIL("이메일 발송에 실패했습니다",400),
    BLACKLIST_ALREADY_EXIST("블랙리스트에 이미 등록되었습니다",400),
    NOT_EXIST_COMMENT("존재하지 않는 댓글입니다", 404),
    ALREADY_INSERT_HEART("이미 좋아요를 누르셨습니다.",400),

    UNAUTHORIZED("권한이 없습니다.", 401),
    FORBIDDEN("권한이 없습니다." , 403),
    EXPIRED_TOKEN("토큰이 만료되었습니다.", 419),
    BAD_REQUEST("잘못된 요청입니다.", 400);

    private final String message;
    private final int status;
}