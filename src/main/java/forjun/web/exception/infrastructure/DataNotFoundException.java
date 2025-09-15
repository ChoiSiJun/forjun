package forjun.web.exception.infrastructure;

import forjun.web.exception.DbException;
import forjun.web.exception.ErrorCode;

public class DataNotFoundException extends DbException {
    public DataNotFoundException(Object... logArgs) {
        super(ErrorCode.DATA_NOT_FOUND, logArgs);
    }
}
