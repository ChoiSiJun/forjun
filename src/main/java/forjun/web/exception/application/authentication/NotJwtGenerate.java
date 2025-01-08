package forjun.web.exception.application.authentication;

import forjun.web.exception.infrastructure.JpaException;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//인증관련 에러 클래스
@Getter
public class NotJwtGenerate extends RuntimeException{

    private static final Logger logger = LoggerFactory.getLogger(JpaException.class);

    public NotJwtGenerate(final String message , String subject , String secretKey) {
        super(message);
        logger.error("Jwt Token 생성실패" + "SubJect : " + subject + "-------"+ "Key : " + secretKey);
    }


}
