package forjun.web.exception.infrastructure;

import forjun.web.exception.DbException;
import forjun.web.exception.ErrorCode;

public class SqlExecutionException extends DbException {
    public SqlExecutionException(Object... logArgs) {
        super(ErrorCode.SQL_EXECUTION_ERROR, logArgs);
    }
}
