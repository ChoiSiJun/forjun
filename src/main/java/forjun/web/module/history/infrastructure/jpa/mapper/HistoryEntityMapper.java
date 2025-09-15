package forjun.web.module.history.infrastructure.jpa.mapper;

import forjun.web.module.history.domain.History;
import forjun.web.module.history.infrastructure.jpa.entity.HistoryEntity;
import forjun.web.module.history.infrastructure.jpa.entity.HistorySkillEntity;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

@Component
public class HistoryEntityMapper {

    public HistoryEntity toEntity(History history) {
        if (history == null) return null;

        HistoryEntity entity = HistoryEntity.builder()
                .userId(history.getUserId())
                .category(history.getCategory())
                .project(history.getProject())
                .subject(history.getSubject())
                .description(history.getDescription())
                .historyStartDate(history.getHistoryStartDate())
                .historyEndDate(history.getHistoryEndDate())
                .build();

        if (history.getHistorySkill() != null) {
            for (String skillName : history.getHistorySkill()) {
                HistorySkillEntity skillEntity = HistorySkillEntity.builder()
                        .skill(skillName)
                        .build();
                entity.addHistorySkill(skillEntity);  // 연관관계 세팅
            }
        }
        return entity;
    }

    public History toDomain(HistoryEntity entity) {
        if (entity == null) return null;

        List<String> skillList = new ArrayList<>();
        if (entity.getHistorySkill() != null) {
            for (HistorySkillEntity skillEntity : entity.getHistorySkill()) {
                skillList.add(skillEntity.getSkill());
            }
        }

        return History.builder()
                .id(entity.getId())
                .category(entity.getCategory())
                .project(entity.getProject())
                .subject(entity.getSubject())
                .description(entity.getDescription())
                .historyStartDate(entity.getHistoryStartDate())
                .historyEndDate(entity.getHistoryEndDate())
                .historySkill(skillList)
                .build();
    }

    public List<History> toDomains(List<HistoryEntity> entities){
        if (entities == null) return null;
        List<History> list = new ArrayList<>();
        for (HistoryEntity entity : entities) {
            list.add(toDomain(entity));
        }
        return list;
    }
}

