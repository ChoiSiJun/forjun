package forjun.web.module.history.api.dto;

import forjun.web.module.history.domain.History;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class HistoryInfoResponse {

    private int historyId;

    private String category;

    private String project;

    private String subject;

    private String description;

    private List<String> HistorySkill = new ArrayList<>();

    private LocalDateTime historyStartDate;

    private LocalDateTime historyEndDate;

    public static HistoryInfoResponse fromDto(History history){

        return HistoryInfoResponse.builder().historyId(history.getHistoryId())
                .category(history.getCategory())
                .description(history.getDescription())
                .project(history.getProject())
                .subject(history.getSubject())
                .historyStartDate(history.getHistoryStartDate())
                .historyEndDate(history.getHistoryEndDate())
                .build();
    }
}
