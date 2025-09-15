package forjun.web.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.slf4j.helpers.MessageFormatter;

public abstract class DbException extends RuntimeException {

    private final ErrorCode errorCode;
    @Getter
    private final String logMessage;
    @Getter
    private final Object[] logArgs;

    public DbException(ErrorCode errorCode, Object... logArgs) {
        super(errorCode.getDisplayMessage());
        this.errorCode = errorCode;
        this.logArgs = logArgs;
        this.logMessage = MessageFormatter.arrayFormat(errorCode.getLogMessage(), logArgs).getMessage();
    }

    public String getErrorCode() {
        return errorCode.getCode();
    }

    public HttpStatus getHttpStatus() {
        return errorCode.getStatus();
    }

    public String getDisplayMessage() {
        return errorCode.getDisplayMessage();
    }

}
