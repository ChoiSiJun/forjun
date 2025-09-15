package forjun.web.exception;

import forjun.web.exception.infrastructure.*;
import forjun.web.exception.server.InternalServerException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.sql.SQLIntegrityConstraintViolationException;

@Component
public class DbExceptionTranslator {

    public DbException translate(Throwable ex) {
        // 1. 중복 키 위반
        if (ex instanceof DuplicateKeyException || isDuplicateKeyCause(ex)) {
            return new DuplicateKeyException("중복 키 위반", ex);
        }

        // 2. 외래 키 위반
        if (isForeignKeyViolation(ex)) {
            return new ForeignKeyConstraintException("외래 키 제약 위반", ex);
        }

        // 3. NOT NULL 제약 위반
        if (isNotNullConstraintViolation(ex)) {
            return new NotNullConstraintException("NOT NULL 제약 위반", ex);
        }

        // 4. 결과 없음
        if (ex instanceof EmptyResultDataAccessException) {
            return new DataNotFoundException("결과 없음", ex);
        }

        // 5. 일반 SQL 실행 오류
        if (ex instanceof DataAccessException) {
            return new SqlExecutionException("SQL 실행 오류", ex);
        }

        // 6. 처리 불가능한 예외 → 서버 오류로 포장
        return new UnknownDbException("처리되지 않은 DB 예외 발생", ex);
    }

    private boolean isDuplicateKeyCause(Throwable ex) {
        return findCause(ex, SQLIntegrityConstraintViolationException.class) != null;
    }

    private boolean isForeignKeyViolation(Throwable ex) {
        ConstraintViolationException cve = findCause(ex, ConstraintViolationException.class);
        return cve != null && constraintNameContains(cve, "fk");
    }

    private boolean isNotNullConstraintViolation(Throwable ex) {
        ConstraintViolationException cve = findCause(ex, ConstraintViolationException.class);
        if (cve != null && cve.getSQLException() != null) {
            String msg = cve.getSQLException().getMessage().toLowerCase();
            return msg.contains("not null");
        }
        return false;
    }

    private boolean constraintNameContains(ConstraintViolationException ex, String keyword) {
        String name = ex.getConstraintName();
        return name != null && name.toLowerCase().contains(keyword);
    }

    private <T extends Throwable> T findCause(Throwable ex, Class<T> type) {
        while (ex != null) {
            if (type.isInstance(ex)) return type.cast(ex);
            ex = ex.getCause();
        }
        return null;
    }
}
