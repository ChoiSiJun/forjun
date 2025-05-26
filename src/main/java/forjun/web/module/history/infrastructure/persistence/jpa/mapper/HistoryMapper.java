package forjun.web.module.history.infrastructure.persistence.jpa.mapper;

import forjun.web.module.history.domain.History;
import forjun.web.module.history.infrastructure.persistence.jpa.HistoryEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HistoryMapper {

    History toHistory(HistoryEntity historyEntity);

    HistoryEntity toHistoryEntity(History history);

    List<History> toHistorys(List<HistoryEntity> historyEntities);

    List<HistoryEntity> toHistoryEntities(List<History> historyEntities);
}
