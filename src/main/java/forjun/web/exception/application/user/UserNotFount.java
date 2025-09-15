package forjun.web.exception.application.user;

import forjun.web.exception.AppException;
import forjun.web.exception.ErrorCode;

public class UserNotFount extends AppException {

    public UserNotFount(String id){
        super(ErrorCode.USER_NOT_FOUND,id);
    }

}
