package forjun.web.exception.infrastructure;

import forjun.web.exception.DbException;
import forjun.web.exception.ErrorCode;

public class DataLengthExceededException extends DbException {
    public DataLengthExceededException(Object... logArgs) {
        super(ErrorCode.DATA_LENGTH_EXCEEDED, logArgs);
    }
}