package forjun.web.domain.history;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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
    @Column(name="history_id")
    private int id;

    private String category;

    private String project;

    private String subject;

    private String description;

    @OneToMany(mappedBy = "history", cascade = CascadeType.ALL , orphanRemoval = true)
    private List<HistorySkillEntity> HistorySkill = new ArrayList<>();

    private LocalDateTime historyStartDate;

    private LocalDateTime historyEndDate;

    @Builder(builderMethodName = "updateHistory")
    public void updateHistory(String category, String project, String subject, String description){

        if(category != null && !category.isEmpty()){
            this.category = category;
        }

        if(project != null && !project.isEmpty()){
            this.project = project;
        }

        if(subject != null && !subject.isEmpty()){
            this.subject = subject;
        }

        if(description != null && !description.isEmpty()){
            this.description = description;
        }
    }
}
