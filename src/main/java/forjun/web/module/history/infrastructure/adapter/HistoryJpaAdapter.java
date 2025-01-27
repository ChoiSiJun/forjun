package forjun.web.module.history.infrastructure.adapter;

import forjun.web.exception.infrastructure.JpaException;
import forjun.web.module.history.application.port.HistoryPort;
import forjun.web.module.history.domain.History;
import forjun.web.module.history.infrastructure.repository.jpa.HistoryEntity;
import forjun.web.module.history.infrastructure.repository.jpa.HistoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class HistoryJpaAdapter implements HistoryPort {

    private final HistoryRepo historyRepo;
    @Override
    public void saveHistory(History history) {
        try {
            historyRepo.save(history.toEntity());
        }catch (Exception e){
            if(history.getHistoryId() == 0){
                throw new JpaException("히스토리 생성 실패","History","insert",e.getCause());
            }else{
                throw new JpaException("히스토리 수정 실패","History","update",e.getCause());
            }
        }
    }

    @Override
    public History getHistory(int historyId) {
        try {
            Optional<HistoryEntity> historyEntity = historyRepo.findById(historyId);
            return historyEntity.map(History::fromEntity).orElse(null);
        }catch (Exception e){
            throw new JpaException("히스토리 조회에 실패하였습니다.", "History", "find", e.getCause());
        }
    }

    @Override
    public List<History> getHistorys(int historyId) {
        try {
            return  historyRepo.findAll().stream()
                    .map(History::fromEntity)
                    .collect(Collectors.toList());

        }catch (Exception e){
            throw new JpaException("히스토리 리스트 조회에 실패하였습니다.", "History", "findAll", e.getCause());
        }
    }

    @Override
    public void deleteHistory(int historyId) {
        try {
            historyRepo.deleteById(historyId);
        } catch (Exception e) {
            throw new JpaException("히스토리 삭제에 실패하였습니다.", "History", "delete", e.getCause());
        }
    }
}
