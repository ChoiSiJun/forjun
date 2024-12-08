package forjun.web.exception;

import forjun.web.exception.authentication.NotJwtGenerate;
import forjun.web.exception.authentication.NotMatchPasswordException;
import forjun.web.exception.authentication.NotJwtValidate;
import forjun.web.exception.user.UserNotCreateException;
import forjun.web.exception.user.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    //유저 관련 Exception
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException e) {
        return new ResponseEntity<>(e.getMessage() , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotCreateException.class)
    public ResponseEntity<String> handleUserNotCreateException(UserNotCreateException e) {
        return new ResponseEntity<>(e.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //인증관련 Exception
    @ExceptionHandler(NotMatchPasswordException.class)
    public ResponseEntity<String> handleUserNotMatchPasswordException(NotMatchPasswordException e) {
        return new ResponseEntity<>(e.getMessage() , HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NotJwtGenerate.class)
    public ResponseEntity<String> handleNotJwtGenerateException(NotJwtGenerate e) {
        log.warn("Jwt Token 생성실패" + "SubJect : " + e.getSubject() + "-------"+ "Key : " + e.getSecretKey());
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
}
