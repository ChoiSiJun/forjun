package forjun.web.exception.application.authentication;

import forjun.web.exception.AppException;
import forjun.web.exception.ErrorCode;

//인증관련 에러 클래스
public class NotJwtGenerate extends AppException {

    public NotJwtGenerate(Exception ex) {
        super(ErrorCode.JWT_NOT_GENERATE, ex);
    }
}
