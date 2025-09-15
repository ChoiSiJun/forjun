package forjun.web.exception.application.authentication;

import forjun.web.exception.AppException;
import forjun.web.exception.ErrorCode;

public class NotMatchPasswordException extends AppException {

    public NotMatchPasswordException(String userId){
        super(ErrorCode.USER_NOT_MATCH_PASSWORD,userId);
    }

}
