package forjun.web.exception.infrastructure;

import forjun.web.exception.DbException;
import forjun.web.exception.ErrorCode;

public class EnumConversionException extends DbException {
    public EnumConversionException(Object... logArgs) {
        super(ErrorCode.ENUM_CONVERSION_FAILED, logArgs);
    }
}