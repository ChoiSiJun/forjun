package forjun.web.exception.application.history;

public class HistoryDuplicateException extends RuntimeException{

    public HistoryDuplicateException(){
        super("이미 존재하는 히스토리 입니다.");
    }

}
