package forjun.web.module.history.application;

import forjun.web.exception.application.content.ContentDuplicateException;
import forjun.web.exception.application.content.ContentNotFoundException;
import forjun.web.exception.application.history.HistoryDuplicateException;
import forjun.web.exception.application.history.HistoryNotFoundException;
import forjun.web.module.content.domain.Content;
import forjun.web.module.history.domain.History;
import forjun.web.module.history.infrastructure.adapter.HistoryAdapter;
import forjun.web.module.history.infrastructure.repository.jpa.HistoryEntity;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class HistoryService {

    private final HistoryAdapter historyAdapter;

    //이력 저장 
    public void saveHistory(History history){
        if(history.getHistoryId() > 0){
            if(historyAdapter.getHistory(history.getHistoryId()) != null){
                throw new HistoryDuplicateException();
            }
        }
        historyAdapter.saveHistory(history);
    }
    
    //이력 수정
    public void updateHistory(History history){
        if(historyAdapter.getHistory(history.getHistoryId()) == null){
            throw new HistoryNotFoundException();
        }
        historyAdapter.saveHistory(history);
    }



    //이력 가지고 오기
    public History getHistory(int historyId){

        History history = historyAdapter.getHistory(historyId);
        if(history == null){throw new ContentNotFoundException();}

        return history;

    }

    //이력 삭제하기
    public void deleteHistory(int historyId){
        historyAdapter.deleteHistory(historyId);
    }
}
