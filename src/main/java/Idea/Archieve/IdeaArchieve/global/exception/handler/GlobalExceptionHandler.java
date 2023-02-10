package Idea.Archieve.IdeaArchieve.global.exception.handler;

import Idea.Archieve.IdeaArchieve.domain.board.exception.NotExistBoardException;
import Idea.Archieve.IdeaArchieve.global.exception.ErrorMessage;
import com.nimbusds.oauth2.sdk.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotExistBoardException.class)
    public ResponseEntity<ErrorMessage> handleNotExistBoardException(HttpServletRequest request , NotExistBoardException exception) {
        printError(request, exception, exception.getErrorCode().getMessage());
        ErrorMessage errorMessage = new ErrorMessage(exception.getErrorCode().getMessage(), exception.getErrorCode().getStatus());
        return new ResponseEntity<>(errorMessage, HttpStatus.valueOf(exception.getErrorCode().getStatus()));
    }

    private void printError(HttpServletRequest request, RuntimeException ex, String message) {
        log.error(request.getRequestURI());
        log.error(message);
        ex.printStackTrace();
    }


}
