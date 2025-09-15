package forjun.web.config.aop;

import forjun.web.exception.DbExceptionTranslator;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

@Aspect
@RequiredArgsConstructor
@Component
public class DbExceptionTranslatorAspect {

    private final DbExceptionTranslator translator;

    @Around("@within(org.springframework.stereotype.Service)")
    public Object translateDataAccessException(ProceedingJoinPoint pjp) throws Throwable {
        try {
            return pjp.proceed();
        } catch (DataAccessException e) {
            throw translator.translate(e);
        }
    }
}
