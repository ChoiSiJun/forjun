package forjun.web.exception.application.history;

public class HistoryNotFoundException extends RuntimeException{

    public HistoryNotFoundException(){
        super("히스토리 정보를 찾을수 없습니다.");
    }
}
