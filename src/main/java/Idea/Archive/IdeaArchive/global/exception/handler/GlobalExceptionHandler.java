package Idea.Archive.IdeaArchive.global.exception.handler;

import Idea.Archive.IdeaArchive.domain.auth.exception.BlackListAlreadyExistException;
import Idea.Archive.IdeaArchive.domain.auth.exception.ExistEmailException;
import Idea.Archive.IdeaArchive.domain.auth.exception.RefreshTokenNotFoundException;
import Idea.Archive.IdeaArchive.domain.comment.exception.NotExistCommentException;
import Idea.Archive.IdeaArchive.domain.email.exception.FailedSendEmailException;
import Idea.Archive.IdeaArchive.domain.email.exception.ManyRequestEmailException;
import Idea.Archive.IdeaArchive.domain.email.exception.MisMatchAuthCodeException;
import Idea.Archive.IdeaArchive.domain.email.exception.NotVerifyEmailException;
import Idea.Archive.IdeaArchive.domain.img.exception.NotExistImageException;
import Idea.Archive.IdeaArchive.domain.member.exception.MemberNotFoundException;
import Idea.Archive.IdeaArchive.domain.member.exception.MisMatchExtensionException;
import Idea.Archive.IdeaArchive.domain.member.exception.MisMatchPasswordException;
import Idea.Archive.IdeaArchive.domain.notice.exception.NotQualifiedDeleteNoticeException;
import Idea.Archive.IdeaArchive.domain.notice.exception.NotQualifiedWriteNoticeException;
import Idea.Archive.IdeaArchive.domain.notice.exception.NoticeNotFoundException;
import Idea.Archive.IdeaArchive.domain.post.exception.AlreadyInsertHeartException;
import Idea.Archive.IdeaArchive.domain.post.exception.ManyCategoryException;
import Idea.Archive.IdeaArchive.domain.post.exception.NotExistPostException;
import Idea.Archive.IdeaArchive.domain.post.exception.NotVerifyMember;
import Idea.Archive.IdeaArchive.global.exception.ErrorMessage;
import Idea.Archive.IdeaArchive.global.security.exception.TokenNotValidException;
import Idea.Archive.IdeaArchive.domain.member.exception.AlreadyExistNicknameException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotExistPostException.class)
    public ResponseEntity<ErrorMessage> handleNotExistBoardException(HttpServletRequest request , NotExistPostException exception) {
        printError(request, exception, exception.getErrorCode().getMessage());
        ErrorMessage errorMessage = new ErrorMessage(exception.getErrorCode().getMessage(), exception.getErrorCode().getStatus());
        return new ResponseEntity<>(errorMessage, HttpStatus.valueOf(exception.getErrorCode().getStatus()));
    }

    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleMemberNotFoundException(HttpServletRequest request , MemberNotFoundException exception) {
        printError(request, exception, exception.getErrorCode().getMessage());
        ErrorMessage errorMessage = new ErrorMessage(exception.getErrorCode().getMessage(), exception.getErrorCode().getStatus());
        return new ResponseEntity<>(errorMessage, HttpStatus.valueOf(exception.getErrorCode().getStatus()));
    }

    @ExceptionHandler(NotVerifyEmailException.class)
    public ResponseEntity<ErrorMessage> handleNotVerifyEmailException(HttpServletRequest request , NotVerifyEmailException exception) {
        printError(request, exception, exception.getErrorCode().getMessage());
        ErrorMessage errorMessage = new ErrorMessage(exception.getErrorCode().getMessage(), exception.getErrorCode().getStatus());
        return new   ResponseEntity<>(errorMessage, HttpStatus.valueOf(exception.getErrorCode().getStatus()));
    }

    @ExceptionHandler(ExistEmailException.class)
    public ResponseEntity<ErrorMessage> handleExistEmailException(HttpServletRequest request , ExistEmailException exception) {
        printError(request, exception, exception.getErrorCode().getMessage());
        ErrorMessage errorMessage = new ErrorMessage(exception.getErrorCode().getMessage(), exception.getErrorCode().getStatus());
        return new ResponseEntity<>(errorMessage, HttpStatus.valueOf(exception.getErrorCode().getStatus()));
    }

    @ExceptionHandler(NotVerifyMember.class)
    public ResponseEntity<ErrorMessage> handleNotVerifyMember(HttpServletRequest request , NotVerifyMember exception) {
        printError(request, exception, exception.getErrorCode().getMessage());
        ErrorMessage errorMessage = new ErrorMessage(exception.getErrorCode().getMessage(), exception.getErrorCode().getStatus());
        return new ResponseEntity<>(errorMessage, HttpStatus.valueOf(exception.getErrorCode().getStatus()));
    }

    @ExceptionHandler(FailedSendEmailException.class)
    public ResponseEntity<ErrorMessage> handleFailedSendEmailException(HttpServletRequest request , FailedSendEmailException exception) {
        printError(request, exception, exception.getErrorCode().getMessage());
        ErrorMessage errorMessage = new ErrorMessage(exception.getErrorCode().getMessage(), exception.getErrorCode().getStatus());
        return new ResponseEntity<>(errorMessage, HttpStatus.valueOf(exception.getErrorCode().getStatus()));
    }

    @ExceptionHandler(ManyRequestEmailException.class)
    public ResponseEntity<ErrorMessage> handleManyRequestEmailException(HttpServletRequest request , ManyRequestEmailException exception) {
        printError(request, exception, exception.getErrorCode().getMessage());
        ErrorMessage errorMessage = new ErrorMessage(exception.getErrorCode().getMessage(), exception.getErrorCode().getStatus());
        return new ResponseEntity<>(errorMessage, HttpStatus.valueOf(exception.getErrorCode().getStatus()));
    }

    @ExceptionHandler(MisMatchAuthCodeException.class)
    public ResponseEntity<ErrorMessage> handleMisMatchAuthCodeException(HttpServletRequest request , MisMatchAuthCodeException exception) {
        printError(request, exception, exception.getErrorCode().getMessage());
        ErrorMessage errorMessage = new ErrorMessage(exception.getErrorCode().getMessage(), exception.getErrorCode().getStatus());
        return new ResponseEntity<>(errorMessage, HttpStatus.valueOf(exception.getErrorCode().getStatus()));
    }


    @ExceptionHandler(MisMatchPasswordException.class)
    public ResponseEntity<ErrorMessage> handleMisMatchPasswordException(HttpServletRequest request , MisMatchPasswordException exception) {
        printError(request, exception, exception.getErrorCode().getMessage());
        ErrorMessage errorMessage = new ErrorMessage(exception.getErrorCode().getMessage(), exception.getErrorCode().getStatus());
        return new ResponseEntity<>(errorMessage, HttpStatus.valueOf(exception.getErrorCode().getStatus()));
    }

    @ExceptionHandler(RefreshTokenNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleRefreshTokenNotFoundException(HttpServletRequest request , RefreshTokenNotFoundException exception) {
        printError(request, exception, exception.getErrorCode().getMessage());
        ErrorMessage errorMessage = new ErrorMessage(exception.getErrorCode().getMessage(), exception.getErrorCode().getStatus());
        return new ResponseEntity<>(errorMessage, HttpStatus.valueOf(exception.getErrorCode().getStatus()));
    }

    @ExceptionHandler(TokenNotValidException.class)
    public ResponseEntity<ErrorMessage> handleTokenNotValidException(HttpServletRequest request , TokenNotValidException exception) {
        printError(request, exception, exception.getErrorCode().getMessage());
        ErrorMessage errorMessage = new ErrorMessage(exception.getErrorCode().getMessage(), exception.getErrorCode().getStatus());
        return new ResponseEntity<>(errorMessage, HttpStatus.valueOf(exception.getErrorCode().getStatus()));
    }

    @ExceptionHandler(BlackListAlreadyExistException.class)
    public ResponseEntity<ErrorMessage> handleBlackListAlreadyExistException(HttpServletRequest request , BlackListAlreadyExistException exception) {
        printError(request, exception, exception.getErrorcode().getMessage());
        ErrorMessage errorMessage = new ErrorMessage(exception.getErrorcode().getMessage(), exception.getErrorcode().getStatus());
        return new ResponseEntity<>(errorMessage, HttpStatus.valueOf(exception.getErrorcode().getStatus()));
    }

    @ExceptionHandler(NotExistCommentException.class)
    public ResponseEntity<ErrorMessage> handleNotExistCommentException(HttpServletRequest request , NotExistCommentException exception) {
        printError(request, exception, exception.getErrorCode().getMessage());
        ErrorMessage errorMessage = new ErrorMessage(exception.getErrorCode().getMessage(), exception.getErrorCode().getStatus());
        return new ResponseEntity<>(errorMessage, HttpStatus.valueOf(exception.getErrorCode().getStatus()));
    }


    @ExceptionHandler(AlreadyInsertHeartException.class)
    public ResponseEntity<ErrorMessage> handleAlreadyInsertHeartException(HttpServletRequest request, AlreadyInsertHeartException exception) {
        printError(request, exception, exception.getErrorCode().getMessage());
        ErrorMessage errorMessage = new ErrorMessage(exception.getErrorCode().getMessage(), exception.getErrorCode().getStatus());
        return new ResponseEntity<>(errorMessage, HttpStatus.valueOf(exception.getErrorCode().getStatus()));
    }

    @ExceptionHandler(AlreadyExistNicknameException.class)
    public ResponseEntity<ErrorMessage> handleAlreadyExistNicknameException(HttpServletRequest request, AlreadyExistNicknameException exception) {
        printError(request,exception,exception.getErrorCode().getMessage());
        ErrorMessage errorMessage = new ErrorMessage(exception.getErrorCode().getMessage(), exception.getErrorCode().getStatus());
        return new ResponseEntity<>(errorMessage,HttpStatus.valueOf(exception.getErrorCode().getStatus()));
    }

    @ExceptionHandler(NotQualifiedWriteNoticeException.class)
    public ResponseEntity<ErrorMessage> handleNotQualifiedWriteNoticeException(HttpServletRequest request, NotQualifiedWriteNoticeException exception) {
        printError(request,exception,exception.getErrorCode().getMessage());
        ErrorMessage errorMessage = new ErrorMessage(exception.getErrorCode().getMessage(), exception.getErrorCode().getStatus());
        return new ResponseEntity<>(errorMessage,HttpStatus.valueOf(exception.getErrorCode().getStatus()));
    }
    @ExceptionHandler(NoticeNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleNoticeNotFoundException(HttpServletRequest request, NoticeNotFoundException exception) {
        printError(request,exception,exception.getErrorCode().getMessage());
        ErrorMessage errorMessage = new ErrorMessage(exception.getErrorCode().getMessage(), exception.getErrorCode().getStatus());
        return new ResponseEntity<>(errorMessage,HttpStatus.valueOf(exception.getErrorCode().getStatus()));
    }
    @ExceptionHandler(NotQualifiedDeleteNoticeException.class)
    public ResponseEntity<ErrorMessage> handleNotQualifiedDeleteNoticeException(HttpServletRequest request, NotQualifiedDeleteNoticeException exception) {
        printError(request,exception,exception.getErrorCode().getMessage());
        ErrorMessage errorMessage = new ErrorMessage(exception.getErrorCode().getMessage(), exception.getErrorCode().getStatus());
        return new ResponseEntity<>(errorMessage,HttpStatus.valueOf(exception.getErrorCode().getStatus()));
    }
    @ExceptionHandler(NotExistImageException.class)
    public ResponseEntity<ErrorMessage> handleNotExistImageException(HttpServletRequest request, NotExistImageException exception) {
        printError(request,exception,exception.getErrorCode().getMessage());
        ErrorMessage errorMessage = new ErrorMessage(exception.getErrorCode().getMessage(), exception.getErrorCode().getStatus());
        return new ResponseEntity<>(errorMessage,HttpStatus.valueOf(exception.getErrorCode().getStatus()));
    }
    @ExceptionHandler(MisMatchExtensionException.class)
    public ResponseEntity<ErrorMessage> handleMisMatchExtensionException(HttpServletRequest request, MisMatchExtensionException exception) {
        printError(request,exception,exception.getErrorCode().getMessage());
        ErrorMessage errorMessage = new ErrorMessage(exception.getErrorCode().getMessage(), exception.getErrorCode().getStatus());
        return new ResponseEntity<>(errorMessage,HttpStatus.valueOf(exception.getErrorCode().getStatus()));
    }

    @ExceptionHandler(ManyCategoryException.class)
    public ResponseEntity<ErrorMessage> handleManyCategoryException(HttpServletRequest request, ManyCategoryException exception) {
        printError(request,exception,exception.getErrorCode().getMessage());
        ErrorMessage errorMessage = new ErrorMessage(exception.getErrorCode().getMessage(), exception.getErrorCode().getStatus());
        return new ResponseEntity<>(errorMessage,HttpStatus.valueOf(exception.getErrorCode().getStatus()));
    }

    private void printError(HttpServletRequest request, RuntimeException ex, String message){
        log.error(request.getRequestURI());
        log.error(message);
        ex.printStackTrace();
    }
}