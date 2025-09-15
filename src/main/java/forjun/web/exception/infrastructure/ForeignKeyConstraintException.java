package forjun.web.exception.infrastructure;

import forjun.web.exception.DbException;
import forjun.web.exception.ErrorCode;

public class ForeignKeyConstraintException extends DbException {
    public ForeignKeyConstraintException(Object... logArgs) {
        super(ErrorCode.FOREIGN_KEY_CONSTRAINT, logArgs);
    }
}
