package forjun.web.module.personal.infrastructure.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "personal")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userId; // 시스템의 사용자 ID (auth/user 서비스와 연결)

    private String name;

    private String job;

    private String profileImageUrl;

    // 경력
    @OneToMany(mappedBy = "personal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PersonalCompanyEntity> companies = new ArrayList<>();

    // 스킬
    @OneToMany(mappedBy = "personal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PersonalSkillEntity> skills = new ArrayList<>();

    // 수상
    @OneToMany(mappedBy = "personal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PersonalAwardEntity> awards = new ArrayList<>();


    // ==========================
    // 연관관계 편의 메서드들
    // ==========================

    public void addCompany(PersonalCompanyEntity company) {
        companies.add(company);
        company.setPersonal(this);
    }

    public void removeCompany(PersonalCompanyEntity company) {
        companies.remove(company);
        company.setPersonal(null);
    }

    public void addSkill(PersonalSkillEntity skill) {
        skills.add(skill);
        skill.setPersonal(this);
    }

    public void removeSkill(PersonalSkillEntity skill) {
        skills.remove(skill);
        skill.setPersonal(null);
    }

    public void addAward(PersonalAwardEntity award) {
        awards.add(award);
        award.setPersonal(this);
    }

    public void removeAward(PersonalAwardEntity award) {
        awards.remove(award);
        award.setPersonal(null);
    }
}
