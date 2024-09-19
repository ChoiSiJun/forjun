package forjun.web.application.history;

import forjun.web.application.history.dto.HistoryDto;
import forjun.web.domain.history.HistoryEntity;
import forjun.web.domain.history.HistoryRepo;
import forjun.web.domain.user.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Table;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class HistoryService {

    private final HistoryRepo historyRepo;

    //이력 저장 및 업데이트
    public HistoryDto setHistory(HistoryDto historyDto){

        HistoryEntity historyEntity = historyRepo.findById(historyDto.getHistoryId())
                .map(history -> {
                    history.updateHistory(historyDto.getCategory(),historyDto.getProject(),historyDto.getSubject(),historyDto.getDescription());
                    return history;
                })
                .orElse(historyDto.toEntity());

        historyEntity = historyRepo.save(historyEntity);

        return HistoryDto.fromEntity(historyEntity);
    }

    //이력 가지고 오기
    public HistoryDto getHistory(int historyId){

        HistoryEntity historyEntity = historyRepo.findById(historyId).orElseThrow( ()-> new EntityNotFoundException("Not Found Entity"));
        return HistoryDto.fromEntity(historyEntity);
        
    }

    //이력 삭제하기
    public void deleteHistory(int historyId){
        try {
            historyRepo.deleteById(historyId);
        }catch(Exception e){
            throw new RuntimeException("Delete History Failed");
        }

    }
}
