package forjun.web.application.history.dto;

import forjun.web.domain.history.HistoryEntity;
import forjun.web.domain.history.HistorySkillEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
public class HistoryDto {

    private int historyId;

    private String category;

    private String project;

    private String subject;

    private String description;

    private List<String> skillList = new ArrayList<>();

    private LocalDateTime historyStartDate;

    private LocalDateTime historyEndDate;

    public HistoryEntity toEntity(){

        List<HistorySkillEntity> historySkillList =
                this.skillList.stream()
                        .map(skill ->
                                HistorySkillEntity.builder()
                                        .skill(skill)
                                        .build())
                        .collect(Collectors.toList());

        return  HistoryEntity.builder()
                .id(this.historyId)
                .category(this.category)
                .project(this.project)
                .subject(this.subject)
                .description(this.description)
                .HistorySkill(historySkillList)
                .historyStartDate(this.historyStartDate)
                .historyEndDate(this.historyEndDate)
                .build();
    }

    public static HistoryDto fromEntity(HistoryEntity historyEntity){

        List<String> skillList = historyEntity.getHistorySkill().stream()
                .map(HistorySkillEntity::getSkill)
                .collect(Collectors.toList());

        return HistoryDto.builder()
                .historyId(historyEntity.getId())
                .category(historyEntity.getCategory())
                .project(historyEntity.getProject())
                .subject(historyEntity.getSubject())
                .skillList(skillList)
                .build();

    }


}
