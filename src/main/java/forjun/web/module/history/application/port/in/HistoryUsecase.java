package forjun.web.module.history.application.port.in;

import forjun.web.exception.application.authentication.NotMatchPasswordException;
import forjun.web.exception.application.user.UserNotFount;
import forjun.web.module.history.domain.History;

public interface HistoryUsecase {

    //히스토리 저장
    void createHistory(History history);

    //히스토리 수정
    void updateHistory(History history);
    
    //히스토리 삭제
    void deleteHistory(Integer historyId);
}
