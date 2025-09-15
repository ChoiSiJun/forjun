package forjun.web.exception.infrastructure;

import forjun.web.exception.DbException;
import forjun.web.exception.ErrorCode;

public class UnknownDbException extends DbException {
    public UnknownDbException(Object... logArgs) {
        super(ErrorCode.UNKNOWN_DB_ERROR, logArgs);
    }
}
