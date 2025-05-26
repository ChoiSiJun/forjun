package forjun.web.module.history.domain;


import forjun.web.module.history.infrastructure.persistence.jpa.HistoryEntity;
import forjun.web.module.history.infrastructure.persistence.jpa.HistorySkillEntity;
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
public class History {

    private int historyId;

    private String category;

    //프로젝트명
    private String project;

    //주제
    private String subject;

    //설명
    private String description;

    //사용스킬
    private List<String> skillList = new ArrayList<>();

    //히스토리 시작날짜
    private LocalDateTime historyStartDate;

    //히스토리 종료날짜
    private LocalDateTime historyEndDate;

    // Entity로 변환
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

    // 도메인으로 변환
    public static History toDomain(HistoryEntity historyEntity){

        List<String> skillList = historyEntity.getHistorySkill().stream()
                .map(HistorySkillEntity::getSkill)
                .collect(Collectors.toList());

        return History.builder()
                .historyId(historyEntity.getId())
                .category(historyEntity.getCategory())
                .project(historyEntity.getProject())
                .subject(historyEntity.getSubject())
                .skillList(skillList)
                .build();

    }


}
