package forjun.web.exception.user;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(){
        super("존재하지 않는 아이디 입니다.");
    }

    public UserNotFoundException(String id, Throwable cause) {
        super("User Id Not Found: " + id, cause);
    }
}
