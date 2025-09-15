package forjun.web.exception.application.user;

import forjun.web.exception.AppException;
import forjun.web.exception.ErrorCode;

public class UserDuplicate extends AppException {

    public UserDuplicate(String pw){
        super(ErrorCode.USER_NOT_MATCH_PASSWORD,pw);
    }

}
