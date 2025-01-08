package forjun.web.exception;

import forjun.web.exception.application.authentication.NotJwtGenerate;
import forjun.web.exception.application.authentication.NotJwtValidate;
import forjun.web.exception.application.authentication.NotMatchPasswordException;
import forjun.web.exception.application.content.ContentDuplicateException;
import forjun.web.exception.application.content.ContentNotFoundException;
import forjun.web.exception.application.history.HistoryDuplicateException;
import forjun.web.exception.application.history.HistoryNotFoundException;
import forjun.web.exception.application.user.UserDuplicateException;
import forjun.web.exception.application.user.UserNotFoundException;
import forjun.web.exception.infrastructure.JpaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //유저 관련 Exception
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException e) {
        return new ResponseEntity<>(e.getMessage() , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserDuplicateException.class)
    public ResponseEntity<String> handleUserDuplicateException(UserDuplicateException e) {
        return new ResponseEntity<>(e.getMessage() , HttpStatus.CONFLICT);
    }

    //콘텐츠 관련 Exception
    @ExceptionHandler(ContentDuplicateException.class)
    public ResponseEntity<String> handleContentDuplicateException(ContentDuplicateException e) {
        return new ResponseEntity<>(e.getMessage() , HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ContentNotFoundException.class)
    public ResponseEntity<String> handleContentNotFoundException(ContentNotFoundException e) {
        return new ResponseEntity<>(e.getMessage() , HttpStatus.NOT_FOUND);
    }

    //히스토리 관련 Exception
    @ExceptionHandler(HistoryDuplicateException.class)
    public ResponseEntity<String> handleHistoryDuplicateException(HistoryDuplicateException e) {
        return new ResponseEntity<>(e.getMessage() , HttpStatus.CONFLICT);
    }

    @ExceptionHandler(HistoryNotFoundException.class)
    public ResponseEntity<String> handleHistoryNotFoundException(HistoryNotFoundException e) {
        return new ResponseEntity<>(e.getMessage() , HttpStatus.NOT_FOUND);
    }

    //인증관련 Exception
    @ExceptionHandler(NotMatchPasswordException.class)
    public ResponseEntity<String> handleUserNotMatchPasswordException(NotMatchPasswordException e) {
        return new ResponseEntity<>(e.getMessage() , HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NotJwtGenerate.class)
    public ResponseEntity<String> handleNotJwtGenerateException(NotJwtGenerate e) {
        return new ResponseEntity<>(e.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotJwtValidate.class)
    public ResponseEntity<String> handleNotJwtValidateException(NotJwtValidate e) {
        return new ResponseEntity<>(e.getMessage() , HttpStatus.UNAUTHORIZED);
    }

    //유효성 관련 Exception
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        // 유효성 검사 실패한 필드와 메시지 처리
        String validateError = Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage();
        return new ResponseEntity<>(validateError, HttpStatus.BAD_REQUEST);
    }

    //JPA 연결 관련 Exception
    @ExceptionHandler(JpaException.class)
    public ResponseEntity<String> handleJPAException(JpaException e) {
        return new ResponseEntity<>(e.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
