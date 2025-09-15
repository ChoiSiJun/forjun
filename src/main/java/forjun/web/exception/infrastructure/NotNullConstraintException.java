package forjun.web.exception.infrastructure;

import forjun.web.exception.DbException;
import forjun.web.exception.ErrorCode;

public class NotNullConstraintException extends DbException {
    public NotNullConstraintException(Object... logArgs) {
        super(ErrorCode.NOT_NULL_CONSTRAINT, logArgs);
    }
}
