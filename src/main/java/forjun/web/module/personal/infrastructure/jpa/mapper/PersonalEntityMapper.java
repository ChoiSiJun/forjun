package forjun.web.module.personal.infrastructure.jpa.mapper;

import forjun.web.module.personal.domain.Personal;
import forjun.web.module.personal.domain.value.PersonalAward;
import forjun.web.module.personal.domain.value.PersonalCompany;
import forjun.web.module.personal.domain.value.PersonalSkill;
import forjun.web.module.personal.infrastructure.jpa.entity.PersonalAwardEntity;
import forjun.web.module.personal.infrastructure.jpa.entity.PersonalCompanyEntity;
import forjun.web.module.personal.infrastructure.jpa.entity.PersonalEntity;
import forjun.web.module.personal.infrastructure.jpa.entity.PersonalSkillEntity;
import org.springframework.stereotype.Component;

@Component
public class PersonalEntityMapper {

    public PersonalEntity toEntity(Personal personal) {
        PersonalEntity personalEntity = PersonalEntity.builder()
                .id(personal.getId())
                .userId(personal.getUserId())
                .name(personal.getName())
                .job(personal.getJob())
                .profileImageUrl(personal.getProfile_image_url())
                .build();

        // Award 매핑
        personal.getPersonalAwards()
                .forEach(award -> {
                    PersonalAwardEntity awardEntity = toEntity(award);
                    personalEntity.addAward(awardEntity); // 편의 메서드
                });

        // Skill 매핑
        personal.getPersonalSkills()
                .forEach(skill -> {
                    PersonalSkillEntity skillEntity = toEntity(skill);
                    personalEntity.addSkill(skillEntity); // 편의 메서드
                });

        // Company 매핑
        personal.getPersonalCompanys()
                .forEach(company -> {
                    PersonalCompanyEntity companyEntity = toEntity(company);
                    personalEntity.addCompany(companyEntity); // 편의 메서드
                });

        return personalEntity;
    }

    // PersonalAward (도메인) -> PersonalAwardEntity (JPA)
    public PersonalAwardEntity toEntity(PersonalAward award) {
        return PersonalAwardEntity.builder()
                .awardName(award.getAward_name())
                .build();
    }

    // PersonalSkill (도메인) -> PersonalSkillEntity (JPA)
    public PersonalSkillEntity toEntity(PersonalSkill skill) {
        return PersonalSkillEntity.builder()
                .skillName(skill.getSkillName())
                .build();
    }

    // PersonalCompany (도메인) -> PersonalCompanyEntity (JPA)
    public PersonalCompanyEntity toEntity(PersonalCompany company) {
        return PersonalCompanyEntity.builder()
                .companyName(company.getCompany_name())
                .startDate(company.getStartDate())
                .endDate(company.getEndDate())
                .build();
    }
}
