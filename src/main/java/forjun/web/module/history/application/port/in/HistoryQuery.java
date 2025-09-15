package forjun.web.module.history.application.port.in;

import forjun.web.module.history.domain.History;

import java.util.List;

public interface HistoryQuery {
    List<History> getHistorys(String category,String userId);

    History getHistory(Integer historyId);
}
