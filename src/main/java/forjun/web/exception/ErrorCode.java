package forjun.web.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    // DOMAIN
    USER_NOT_FOUND(
            "존재하지 않는 이용자입니다.",
            "존재하지 않는 이용자입니다 -> ID:{}",
            "USER_NOT_FOUND",
            HttpStatus.NOT_FOUND
    ),
    USER_DUPLICATE(
            "이미 존재하는 이용자 입니다.",
            "이미 존재하는 이용자 입니다. -> ID:{}",
            "USER_DUPLICATE",
            HttpStatus.CONFLICT
    ),
    USER_NOT_MATCH_PASSWORD(
            "패스워드가 일치하지 않습니다",
            "패스워드 불일치 -> PW:{}",
            "NOT_MATCH_PASSWORD",
            HttpStatus.UNAUTHORIZED
    ),
    //인증 인가
    AUTHENTICATION_FAILED("인증에 실패하였습니다.", "AUTHENTICATION_FAILED", "AUTHENTICATION_FAILED",HttpStatus.UNAUTHORIZED),
    ACCESS_DENIED("접근 권한이 없습니다.", "ACCESS_DENIED","ACCESS_DENIED", HttpStatus.FORBIDDEN),

    // JWT
    JWT_NOT_GENERATE(
            "토큰 생성을 실패하였습니다.",
            "토큰 생성 실패 -> 원인:{}",
            "JWT_NOT_GENERATE",
            HttpStatus.UNAUTHORIZED
    ),
    JWT_TOKEN_INVALID(
            "토큰 검증에 실패하였습니다.",
            "토큰 검증 실패 -> 원인:{}",
            "JWT_TOKEN_INVALID",
            HttpStatus.UNAUTHORIZED
    ),
    JWT_TOKEN_EXPIRED(
            "토큰이 만료되었습니다.",
            "토큰 만료 -> 만료시간:{}",
            "JWT_TOKEN_EXPIRED",
            HttpStatus.UNAUTHORIZED
    ),

    // DB
    UNKNOWN_DB_ERROR("처리되지 않은 DB 오류가 발생했습니다", "UNKNOWN_DB_ERROR -> 원인: {}", "UNKNOWN_DB_ERROR", HttpStatus.INTERNAL_SERVER_ERROR),
    DATA_NOT_FOUND(
            "데이터를 찾을 수 없습니다.",
            "데이터 없음 -> 조건:{}",
            "DATA_NOT_FOUND",
            HttpStatus.NOT_FOUND
    ),
    DUPLICATE_KEY(
            "중복되는 키입니다.",
            "중복 키 발생 -> 키:{}",
            "DUPLICATE_KEY",
            HttpStatus.CONFLICT
    ),
    FOREIGN_KEY_CONSTRAINT("삭제할 수 없습니다. 참조 중인 데이터가 있습니다.", "외래키 제약 오류 -> 대상:{}",
            "FOREIGN_KEY_CONSTRAINT", HttpStatus.CONFLICT),

    NOT_NULL_CONSTRAINT("필수 입력 항목이 누락되었습니다.", "NULL 제약 오류 -> 컬럼:{}",
            "NOT_NULL_CONSTRAINT", HttpStatus.BAD_REQUEST),

    DATA_LENGTH_EXCEEDED("입력한 데이터 길이가 너무 깁니다.", "데이터 길이 초과 -> 필드:{}",
            "DATA_LENGTH_EXCEEDED", HttpStatus.BAD_REQUEST),

    ENUM_CONVERSION_FAILED("잘못된 코드값이 전달되었습니다.", "Enum 변환 실패 -> 값:{}",
            "ENUM_CONVERSION_FAILED", HttpStatus.BAD_REQUEST),

    SQL_EXECUTION_ERROR("쿼리 실행 중 오류가 발생했습니다.", "SQL 실행 오류 -> 쿼리:{}",
            "SQL_EXECUTION_ERROR", HttpStatus.INTERNAL_SERVER_ERROR),

    TRANSACTION_FAILED("요청 처리 중 문제가 발생했습니다.", "트랜잭션 실패 -> 원인:{}",
            "TRANSACTION_FAILED", HttpStatus.INTERNAL_SERVER_ERROR),

    // SERVER
    SERVER_ERROR(
            "예상치 못한 서버 오류가 발생하였습니다.",
            "서버 오류 발생 -> 원인:{}",
            "SERVER_ERROR",
            HttpStatus.INTERNAL_SERVER_ERROR
    );

    private final String displayMessage; // 사용자에게 보여줄 메시지
    private final String logMessage;     // 로그용 메시지
    private final String code;           // 시스템 에러코드
    private final HttpStatus status;

    ErrorCode(String displayMessage, String logMessage, String code, HttpStatus status) {
        this.displayMessage = displayMessage;
        this.logMessage = logMessage;
        this.code = code;
        this.status = status;
    }
}
