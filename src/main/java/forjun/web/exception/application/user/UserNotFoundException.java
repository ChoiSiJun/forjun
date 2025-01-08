package forjun.web.exception.application.user;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(){
        super("이용자 정보를 찾을수 없습니다.");
    }
}
