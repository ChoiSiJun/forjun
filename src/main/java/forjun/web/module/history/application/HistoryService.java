package forjun.web.module.history.application;

import forjun.web.exception.AppException;
import forjun.web.exception.ErrorCode;
import forjun.web.module.history.application.port.in.HistoryQuery;
import forjun.web.module.history.application.port.in.HistoryUsecase;
import forjun.web.module.history.domain.History;
import forjun.web.module.history.application.port.out.HistoryJpaPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class HistoryService implements HistoryQuery , HistoryUsecase {

    private final HistoryJpaPort historyJpaPort;

    @Override
    public void createHistory(History history) {
        historyJpaPort.saveHistory(history);
    }

    @Override
    public void updateHistory(History history) {
        historyJpaPort.updateHistory(history);
    }

    @Override
    public void deleteHistory(Integer historyId) {

        if(historyJpaPort.existsHistory(historyId)) {
            throw AppException.of(ErrorCode.HISTORY_NOT_FOUND,historyId);
        }
        historyJpaPort.deleteHistory(historyId);
    }

    @Override
    public List<History> getHistorys(String category,String userId) {
        return historyJpaPort.getHistorys(category , userId);
    }

    @Override
    public History getHistory(Integer historyId) {

        Optional<History> historyOptional= historyJpaPort.getHistory(historyId);
        if(historyOptional.isEmpty()){
            throw AppException.of(ErrorCode.HISTORY_NOT_FOUND,historyId);
        }

        return historyOptional.get();
    }
}
