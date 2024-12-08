package forjun.web.exception.authentication;

public class NotMatchPasswordException extends RuntimeException{

    public NotMatchPasswordException(){
        super("비밀번호가 일치하지 않습니다.");
    }

    public NotMatchPasswordException(String id, Throwable cause) {
        super("비밀번호가 일치하지 않습니다. -> " + id, cause);
    }
}
