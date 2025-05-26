package forjun.web.module.history.infrastructure.persistence.jpa.adapter;

import forjun.web.exception.infrastructure.JpaException;
import forjun.web.module.history.domain.port.HistoryPort;
import forjun.web.module.history.domain.History;
import forjun.web.module.history.infrastructure.persistence.jpa.HistoryEntity;
import forjun.web.module.history.infrastructure.persistence.jpa.HistoryRepo;
import forjun.web.module.history.infrastructure.persistence.jpa.mapper.HistoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class HistoryJpaAdapter implements HistoryPort {

    private final HistoryMapper historyMapper;
    private final HistoryRepo historyRepo;
    @Override
    public void saveHistory(History history) {
        historyRepo.save(historyMapper.toHistoryEntity(history));
    }

    @Override
    public Optional<History> getHistory(int historyId) {
        return historyRepo.findById(historyId).map(historyMapper::toHistory);
    }

    @Override
    public List<History> getHistorys(int historyId) {
        return  historyMapper.toHistorys(historyRepo.findAll());
    }

    @Override
    public void deleteHistory(int historyId) {
        historyRepo.deleteById(historyId);
    }
}
