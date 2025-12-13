package forjun.web.module.personal.infrastructure.jpa.entity;

import forjun.web.module.personal.domain.Personal;
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

    /** 사용자 ID */
    @Column(nullable = false , unique = true)
    private String userId; 

    /** 이름 */
    private String name;

    /** 직업 */
    private String job;

    /** 프로필 이미지 URL */
    private String profileImageUrl;

    // 경력
    @Builder.Default
    @OneToMany(mappedBy = "personal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PersonalCompanyEntity> companies = new ArrayList<>();

    // 스킬
    @Builder.Default
    @OneToMany(mappedBy = "personal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PersonalSkillEntity> skills = new ArrayList<>();

    // 수상
    @Builder.Default
    @OneToMany(mappedBy = "personal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PersonalAwardEntity> awards = new ArrayList<>();

    // ==========================
    // 기능 메서드
    // ==========================

    /** 자기소개서 업데이트 */
    public PersonalEntity update(Personal personal ,
                                 List<PersonalAwardEntity> newAwards,
                                 List<PersonalSkillEntity> newSkills,
                                 List<PersonalCompanyEntity> newCompanies)
    {
        this.name = personal.getName();
        this.job = personal.getJob();

        if(personal.getProfile_image_url() != null){
         this.profileImageUrl = personal.getProfile_image_url();
        }

        this.companies.clear();
        newCompanies.forEach(this::addCompany);

        this.skills.clear();
        newSkills.forEach(this::addSkill);

        this.awards.clear();
        newAwards.forEach(this::addAward);
        return this;
    }

    // ==========================
    // 연관관계 편의 메서드들
    // ==========================

    /** 경력 추가 */
    public void addCompany(PersonalCompanyEntity company) {
        companies.add(company);
        company.setPersonal(this);
    }

    /** 경력 삭제 */
    public void removeCompany(PersonalCompanyEntity company) {
        companies.remove(company);
        company.setPersonal(null);
    }

    /** 스킬 추가 */
    public void addSkill(PersonalSkillEntity skill) {
        skills.add(skill);
        skill.setPersonal(this);
    }

    /** 스킬 삭제 */
    public void removeSkill(PersonalSkillEntity skill) {
        skills.remove(skill);
        skill.setPersonal(null);
    }

    /** 수상 추가 */
    public void addAward(PersonalAwardEntity award) {
        awards.add(award);
        award.setPersonal(this);
    }

    /** 수상 삭제 */
    public void removeAward(PersonalAwardEntity award) {
        awards.remove(award);
        award.setPersonal(null);
    }
}
