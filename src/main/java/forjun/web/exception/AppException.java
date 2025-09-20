package forjun.web.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class AppException extends RuntimeException {

    private final ErrorCode errorCode;
    @Getter
    private final transient Object[] logArgs; // 로그 메시지 대입값 (옵션)

    public AppException(ErrorCode errorCode, Object... logArgs) {
        super(errorCode.getDisplayMessage()); // 사용자 메시지만 super에 전달
        this.errorCode = errorCode;
        this.logArgs = logArgs;
    }

    public String getDisplayMessage() {
        return errorCode.getDisplayMessage();
    }

    public String getLogMessage() {
        return errorCode.getLogMessage();
    }

    public String getErrorCode() {
        return errorCode.getCode();
    }

    public HttpStatus getHttpStatus() {
        return errorCode.getStatus();
    }

    public static AppException of(ErrorCode errorCode, Object... logArgs) {
        return new AppException(errorCode , logArgs);
    }

}
