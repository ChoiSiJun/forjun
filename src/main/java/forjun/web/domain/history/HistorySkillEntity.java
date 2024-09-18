package forjun.web.domain.history;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HistorySkillEntity {

    @Id
    @GeneratedValue
    @Column(name="history_skill_id")
    private int id;

    @ManyToOne
    @JoinColumn(name="history_id")
    private HistoryEntity history;

    private String skill;

}
