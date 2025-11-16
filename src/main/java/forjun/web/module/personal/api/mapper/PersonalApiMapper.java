package forjun.web.module.personal.api.mapper;

import forjun.web.module.personal.api.dto.PersonalResponseDto;
import forjun.web.module.personal.api.dto.SavePersonalRequestDto;
import forjun.web.module.personal.domain.Personal;
import forjun.web.module.personal.domain.value.PersonalAward;
import forjun.web.module.personal.domain.value.PersonalCompany;
import forjun.web.module.personal.domain.value.PersonalSkill;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class PersonalApiMapper {

    @Value("${file.upload.resource-domain}")
    private String resourceDomain;
 
    @Value("${file.upload.resource-url}")
    private String resourceUrl;

    public Personal toDomain(String userId, SavePersonalRequestDto request) {
        Personal personal = Personal.builder()
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


    public PersonalResponseDto toPersonalResponseDto(Personal personal) {

        if(personal == null){
            return null;
        }

        List<PersonalResponseDto.PersonalAwardResponse> awardList = new ArrayList<>();
        List<PersonalResponseDto.PersonalCompanyResponse> companyList = new ArrayList<>();
        List<PersonalResponseDto.PersonalSkillResponse> skillList = new ArrayList<>();

        personal.getPersonalAwards().forEach(award -> {
            awardList.add(PersonalResponseDto.PersonalAwardResponse.builder()
                    .awardName(award.getAward_name())
                    .build());
        });

        personal.getPersonalSkills().forEach(skill -> {
            skillList.add(PersonalResponseDto.PersonalSkillResponse.builder().skillName(skill.getSkillName()).build());
        });

        personal.getPersonalCompanys().forEach(company -> {
            companyList.add(PersonalResponseDto.PersonalCompanyResponse.builder()
                    .companyName(company.getCompany_name())
                    .startDate(company.getStartDate())
                    .endDate(company.getEndDate())
                    .build());
        });


        return PersonalResponseDto.builder()
                .id(personal.getId())
                .name(personal.getName())
                .job(personal.getJob())
                .profile_image_url(resourceDomain + resourceUrl + personal.getProfile_image_url())
                .awards(awardList)
                .companies(companyList)
                .skills(skillList)
                .build();
    }
}
