package forjun.web.module.history.application.port;

import forjun.web.module.history.domain.History;

import java.util.List;

public interface HistoryPort {

    public void saveHistory(History history);

    public History getHistory(int historyId);

    public List<History> getHistorys(int historyId);

    public void deleteHistory(int historyId);
}
