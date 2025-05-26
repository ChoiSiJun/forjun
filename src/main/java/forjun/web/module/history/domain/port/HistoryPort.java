package forjun.web.module.history.domain.port;

import forjun.web.module.history.domain.History;

import java.util.List;
import java.util.Optional;

public interface HistoryPort {

    public void saveHistory(History history);

    public Optional<History> getHistory(int historyId);

    public List<History> getHistorys(int historyId);

    public void deleteHistory(int historyId);
}
