package forjun.web.module.history.application;

import forjun.web.exception.application.content.ContentNotFoundException;
import forjun.web.exception.application.history.HistoryDuplicateException;
import forjun.web.exception.application.history.HistoryNotFoundException;
import forjun.web.module.history.domain.History;
import forjun.web.module.history.infrastructure.adapter.HistoryJpaAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class HistoryService {

    private final HistoryJpaAdapter historyJpaAdapter;

    //이력 저장 
    public void saveHistory(History history){
        if(history.getHistoryId() > 0){
            if(historyJpaAdapter.getHistory(history.getHistoryId()) != null){
                throw new HistoryDuplicateException();
            }
        }
        historyJpaAdapter.saveHistory(history);
    }
    
    //이력 수정
    public void updateHistory(History history){
        if(historyJpaAdapter.getHistory(history.getHistoryId()) == null){
            throw new HistoryNotFoundException();
        }
        historyJpaAdapter.saveHistory(history);
    }



    //이력 가지고 오기
    public History getHistory(int historyId){

        History history = historyJpaAdapter.getHistory(historyId);
        if(history == null){throw new ContentNotFoundException();}

        return history;

    }

    //이력 삭제하기
    public void deleteHistory(int historyId){
        historyJpaAdapter.deleteHistory(historyId);
    }
}
