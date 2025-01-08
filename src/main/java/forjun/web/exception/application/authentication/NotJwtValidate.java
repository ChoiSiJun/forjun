package forjun.web.exception.application.authentication;

//인증관련 에러 클래스
public class NotJwtValidate extends RuntimeException{

    private String notValidateCause;

    public NotJwtValidate(final String message , String notValidateCause) {
        super(message);
        this.notValidateCause = notValidateCause;
    }

    public String getNotValidateCause() {
        return notValidateCause;
    }
}
