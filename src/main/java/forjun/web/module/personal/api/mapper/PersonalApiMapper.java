package forjun.web.module.personal.api.mapper;

import forjun.web.module.personal.api.dto.SavePersonalRequestDto;
import forjun.web.module.personal.domain.Personal;
import forjun.web.module.personal.domain.value.PersonalAward;
import forjun.web.module.personal.domain.value.PersonalCompany;
import forjun.web.module.personal.domain.value.PersonalSkill;
import org.springframework.stereotype.Component;


@Component
public class PersonalApiMapper {

    public Personal toDomain(String userId, SavePersonalRequestDto request) {
        Personal personal = Personal.builder()
                .id(request.getId())
                .userId(userId)
                .name(request.getName())
                .job(request.getJob())
                .profile_image_url(request.getProfile_image_url())
                .build();

        // Awards
        request.getAwards().forEach(a ->
                personal.getPersonalAwards().add(
                        PersonalAward.builder()
                                .award_name(a.getAwardName())
                                .build()
                )
        );

        // Skills
        request.getSkills().forEach(s ->
                personal.getPersonalSkills().add(
                        PersonalSkill.builder()
                                .skillName(s.getSkillName())
                                .build()
                )
        );

        // Companies
        request.getCompanies().forEach(c ->
                personal.getPersonalCompanys().add(
                        PersonalCompany.builder()
                                .company_name(c.getCompanyName())
                                .startDate(c.getStartDate())
                                .endDate(c.getEndDate())
                                .build()
                )
        );

        return personal;
    }
}
