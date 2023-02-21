package Idea.Archieve.IdeaArchieve.global.exception.handler;

import Idea.Archieve.IdeaArchieve.domain.auth.exception.BlackListAlreadyExistException;
import Idea.Archieve.IdeaArchieve.domain.auth.exception.ExistEmailException;
import Idea.Archieve.IdeaArchieve.domain.auth.exception.RefreshTokenNotFoundException;
import Idea.Archieve.IdeaArchieve.domain.email.exception.FailedSendEmailException;
import Idea.Archieve.IdeaArchieve.domain.email.exception.ManyRequestEmailException;
import Idea.Archieve.IdeaArchieve.domain.email.exception.MisMatchAuthCodeException;
import Idea.Archieve.IdeaArchieve.domain.email.exception.NotVerifyEmailException;
import Idea.Archieve.IdeaArchieve.domain.member.exception.MemberNotFoundException;
import Idea.Archieve.IdeaArchieve.domain.member.exception.MisMatchPasswordException;
import Idea.Archieve.IdeaArchieve.domain.post.exception.NotExistPostException;
import Idea.Archieve.IdeaArchieve.domain.post.exception.NotVerifyMember;
import Idea.Archieve.IdeaArchieve.global.exception.ErrorMessage;
import Idea.Archieve.IdeaArchieve.global.security.exception.TokenNotValidException;
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

    private void printError(HttpServletRequest request, RuntimeException ex, String message) {
        log.error(request.getRequestURI());
        log.error(message);
        ex.printStackTrace();
    }


}
