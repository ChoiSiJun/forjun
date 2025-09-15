package forjun.web.exception.application.authentication;

import forjun.web.exception.AppException;
import forjun.web.exception.ErrorCode;

//인증관련 에러 클래스
public class NotJwtValidate extends AppException {

    public NotJwtValidate(Exception ex) {
        super(ErrorCode.JWT_TOKEN_INVALID,ex);
    }
}
