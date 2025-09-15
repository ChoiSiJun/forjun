package forjun.web.module.history.infrastructure.jpa;

import forjun.web.module.history.application.port.out.HistoryJpaPort;
import forjun.web.module.history.domain.History;
import forjun.web.module.history.infrastructure.jpa.entity.HistoryEntity;
import forjun.web.module.history.infrastructure.jpa.entity.HistorySkillEntity;
import forjun.web.module.history.infrastructure.jpa.mapper.HistoryEntityMapper;
import forjun.web.module.history.infrastructure.jpa.repository.HistoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class HistoryJpaAdapter implements HistoryJpaPort {

    private final HistoryEntityMapper historyEntityMapper;
    private final HistoryRepo historyRepo;

    @Override
    public void saveHistory(History history) {

        HistoryEntity historyEntity = historyEntityMapper.toEntity(history);
        historyRepo.save(historyEntity);
    }

    @Override
    public History getHistory(Integer historyId) {
        HistoryEntity entity = historyRepo.findById(historyId).orElse( null);
        if (entity == null){
            return null;
        }
        return  historyEntityMapper.toDomain(entity);
    }

    @Override
    public List<History> getHistorys(String category , String userId) {
        return historyEntityMapper.toDomains(historyRepo.findByCategoryAndUserIdOrderByHistoryStartDateDesc(category , userId));
    }

    @Override
    public void deleteHistory(int historyId) {
        historyRepo.deleteById(historyId);
    }
}
