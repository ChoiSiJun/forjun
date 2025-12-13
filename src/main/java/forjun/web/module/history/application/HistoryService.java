package forjun.web.module.history.application;

import forjun.web.exception.AppException;
import forjun.web.exception.ErrorCode;
import forjun.web.module.history.application.port.in.HistoryQuery;
import forjun.web.module.history.application.port.in.HistoryUsecase;
import forjun.web.module.history.application.port.in.dto.CreateHistoryCommand;
import forjun.web.module.history.application.port.in.dto.GetHistoryQuery;
import forjun.web.module.history.application.port.in.dto.GetHistorysQuery;
import forjun.web.module.history.application.port.in.dto.UpdateHistoryCommand;
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
    public void createHistory(CreateHistoryCommand command) {

        //히스토리 도메인 생성 후 저장
        historyJpaPort.saveHistory(HistoryFactory.createHistory(command));
    }

    @Override
    public void updateHistory(UpdateHistoryCommand command) {

        //히스토리 도메인 생성 후 수정
        historyJpaPort.updateHistory(HistoryFactory.createHistory(command));
    }

    @Override
    public void deleteHistory(Integer historyId) {

        //히스토리 존재여부 체크
        if(historyJpaPort.existsHistory(historyId)) {
            throw AppException.of(ErrorCode.HISTORY_NOT_FOUND, historyId);
        }

        //히스토리 삭제
        historyJpaPort.deleteHistory(historyId);
    }

    @Override
    public List<History> getHistorys(GetHistorysQuery query) {

        //히스토리 리스트 조회
        return historyJpaPort.getHistorys(query.category() , query.userId());
    }

    @Override
    public History getHistory(GetHistoryQuery query) {

        //히스토리 조회
        Optional<History> historyOptional= historyJpaPort.getHistory(query.historyId());

        //히스토리 존재여부 체크
        if(historyOptional.isEmpty()){
            //히스토리 존재하지 않음
            throw AppException.of(ErrorCode.HISTORY_NOT_FOUND,query.historyId());
        }

        //히스토리 반환
        return historyOptional.get();
    }
}
