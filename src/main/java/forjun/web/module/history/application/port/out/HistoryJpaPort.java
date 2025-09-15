package forjun.web.module.history.application.port.out;

import forjun.web.module.history.domain.History;

import java.util.List;

public interface HistoryJpaPort {

    //저장
    void saveHistory(History history);

    //개별 조회
    History getHistory(Integer historyId);

    //리스트 조회
    List<History> getHistorys(String category , String userId);
    
    //삭제
    void deleteHistory(int historyId);
}
