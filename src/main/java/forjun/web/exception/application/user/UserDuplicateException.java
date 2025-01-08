package forjun.web.exception.application.user;

public class UserDuplicateException extends RuntimeException{

    public UserDuplicateException(){
        super("이미 존재하는 이용자입니다.");
    }

}
