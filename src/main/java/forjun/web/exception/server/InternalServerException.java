package forjun.web.exception.server;

import forjun.web.exception.AppException;
import forjun.web.exception.ErrorCode;

public class InternalServerException extends AppException {
    public InternalServerException(Object... logArgs) {
        super(ErrorCode.SERVER_ERROR, logArgs);
    }
}