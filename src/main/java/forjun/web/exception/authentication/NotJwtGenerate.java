package forjun.web.exception.authentication;

import lombok.Getter;

//인증관련 에러 클래스
@Getter
public class NotJwtGenerate extends RuntimeException{

    private final String subject;
    private final String secretKey;

    public NotJwtGenerate(final String message , String subject , String secretKey) {
        super(message);
        this.subject = subject;
        this.secretKey = secretKey;
    }


}
