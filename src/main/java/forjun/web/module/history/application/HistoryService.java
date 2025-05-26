package forjun.web.module.history.application;

import forjun.web.exception.application.history.HistoryNotFoundException;
import forjun.web.exception.infrastructure.JpaException;
import forjun.web.module.history.domain.History;
import forjun.web.module.history.domain.port.HistoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class HistoryService {

    private final HistoryPort historyPort;

    //이력 저장 
    public void createHistory(History history) {

        try {
            historyPort.saveHistory(history);
        } catch (DataAccessException e) {
            throw new JpaException("DB 처리 중 오류가 발생했습니다.", "History", "CreateHistory", e.getCause());
        }
    }

    //이력 수정
    public void updateHistory(History history) {


        try {
            historyPort.getHistory(history.getHistoryId()).orElseThrow(
                    HistoryNotFoundException::new);
            historyPort.saveHistory(history);

        } catch (DataAccessException e) {
            throw new JpaException("DB 처리 중 오류가 발생했습니다.", "History", "updateHistory", e.getCause());
        }

    }

    //이력 가지고 오기
    public History getHistory(int historyId) {

        try {
            return historyPort.getHistory(historyId).orElseThrow(
                    HistoryNotFoundException::new
            );

        } catch (DataAccessException e) {
            throw new JpaException("DB 처리 중 오류가 발생했습니다.", "History", "updateHistory", e.getCause());
        }
    }

    //이력 삭제하기
    public void deleteHistory(int historyId) {

        try {
            historyPort.getHistory(historyId).orElseThrow(HistoryNotFoundException::new);
            historyPort.deleteHistory(historyId);

        } catch (DataAccessException e) {
            throw new JpaException("DB 처리 중 오류가 발생했습니다.", "History", "deleteHistory", e.getCause());
        }
    }
}
