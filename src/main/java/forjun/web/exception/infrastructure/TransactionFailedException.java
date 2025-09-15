package forjun.web.exception.infrastructure;

import forjun.web.exception.DbException;
import forjun.web.exception.ErrorCode;

public class TransactionFailedException extends DbException {
    public TransactionFailedException(Object... logArgs) {
        super(ErrorCode.TRANSACTION_FAILED, logArgs);
    }
}
