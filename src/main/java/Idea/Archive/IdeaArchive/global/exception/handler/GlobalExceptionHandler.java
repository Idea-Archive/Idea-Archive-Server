package Idea.Archive.IdeaArchive.global.exception.handler;

import Idea.Archive.IdeaArchive.global.exception.response.ErrorMessage;
import Idea.Archive.IdeaArchive.global.exception.IdeaArchiveException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IdeaArchiveException.class)
    public ResponseEntity<ErrorMessage> globalExceptionHandler(IdeaArchiveException e) {
        printError(e, e.getErrorCode().getMessage());
        return new ResponseEntity<>(new ErrorMessage(e.getErrorCode()), HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    private void printError(RuntimeException ex, String message) {
        log.error(message);
        ex.printStackTrace();
    }
}