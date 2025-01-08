package forjun.web.exception.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//JPA 및 Entity 에 대한 Exception
public class JpaException extends RuntimeException{

    private static final Logger logger = LoggerFactory.getLogger(JpaException.class);

    public JpaException(String message, String entity , String action , Throwable cause){
        super(message , cause);
        logException(entity , action , cause);
    }

    private void logException(String Entity, String action, Throwable cause) {
        // 예외가 발생한 원인과 메시지를 로깅합니다.
        logger.error("JPAException: {" + "Entity:"+ Entity + "action:" + action +"}", cause);
    }
}
