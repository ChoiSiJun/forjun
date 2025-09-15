package forjun.web.exception.infrastructure;

import forjun.web.exception.DbException;
import forjun.web.exception.ErrorCode;

public class DuplicateKeyException extends DbException {
    public DuplicateKeyException(Object... logArgs) {
        super(ErrorCode.DUPLICATE_KEY, logArgs);
    }
}
