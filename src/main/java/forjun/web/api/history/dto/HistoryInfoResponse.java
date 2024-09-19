package forjun.web.api.history.dto;

import forjun.web.application.history.dto.HistoryDto;
import forjun.web.domain.history.HistorySkillEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
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

    public static HistoryInfoResponse fromDto(HistoryDto historyDto){

        return HistoryInfoResponse.builder().historyId(historyDto.getHistoryId())
                .category(historyDto.getCategory())
                .description(historyDto.getDescription())
                .project(historyDto.getProject())
                .subject(historyDto.getSubject())
                .historyStartDate(historyDto.getHistoryStartDate())
                .historyEndDate(historyDto.getHistoryEndDate())
                .build();
    }
}
