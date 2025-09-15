package forjun.web.module.history.api.mapper;

import forjun.web.module.history.api.dto.HistoryCreateRequest;
import forjun.web.module.history.api.dto.HistoryResponse;
import forjun.web.module.history.api.dto.HistoryUpdateRequest;
import forjun.web.module.history.domain.History;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HistoryApiMapper {

    @Mapping(target = "userId" , source = "userId")
    History toDomain(String userId, HistoryCreateRequest request);

    @Mapping(target = "userId" , source = "userId")
    History toDomain(String userId, HistoryUpdateRequest request);

    //단일객체
    HistoryResponse toHistoryResponse(History history);
    
    //리스트 객체
    List<HistoryResponse> toHistoryReponseList(List<History> historyList);
}
