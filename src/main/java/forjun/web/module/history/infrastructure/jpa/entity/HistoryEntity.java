package forjun.web.module.history.infrastructure.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="history")
public class HistoryEntity {

    @Id
    @GeneratedValue
    private Integer id;

    private String userId;

    private String category;

    private String project;

    private String subject;

    private String description;

    @Builder.Default
    @OneToMany(mappedBy = "history", cascade = CascadeType.ALL , orphanRemoval = true)
    private List<HistorySkillEntity> historySkill = new ArrayList<>();

    private LocalDate historyStartDate;

    private LocalDate  historyEndDate;

    public void addHistorySkill(HistorySkillEntity skill) {

        historySkill.add(skill);
        skill.setHistory(this);
    }
}
