package forjun.web.exception.user;

public class UserNotCreateException extends RuntimeException{

    public UserNotCreateException(String id){
        super("이용자 생성에 실패했습니다." + id);
    }
}
