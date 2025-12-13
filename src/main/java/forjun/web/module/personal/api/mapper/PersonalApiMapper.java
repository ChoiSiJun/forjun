package forjun.web.module.personal.api.mapper;

import forjun.web.module.personal.api.dto.PersonalDetailResponseDto;
import forjun.web.module.personal.api.dto.SavePersonalRequestDto;
import forjun.web.module.personal.application.port.in.dto.SavePersonalCommand;
import forjun.web.module.personal.domain.Personal;
import forjun.web.module.personal.domain.value.PersonalAward;
import forjun.web.module.personal.domain.value.PersonalCompany;
import forjun.web.module.personal.domain.value.PersonalSkill;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/** 자기소개서 API Mapper */

@Component
public class PersonalApiMapper {

    @Value("${file.upload.resource-domain}")
    private String resourceDomain;
 
    @Value("${file.upload.resource-url}")
    private String resourceUrl;

    /** 자기소개서 도메인 변환 */
    public SavePersonalCommand toSavePersonalCommand(String userId, SavePersonalRequestDto request) {
        
        List<SavePersonalCommand.PersonalAward> awards = new ArrayList<>();
        List<SavePersonalCommand.PersonalSkill> skills = new ArrayList<>();
        List<SavePersonalCommand.PersonalCompany> companies = new ArrayList<>();

        // Awards
        request.awards().forEach(a -> awards.add(new SavePersonalCommand.PersonalAward(a.awardName())));

        // Skills
        request.skills().forEach(s -> skills.add(new SavePersonalCommand.PersonalSkill(s.skillName())));

        // Companies
        request.companies().forEach(c -> companies.add(new SavePersonalCommand.PersonalCompany(c.companyName(), c.startDate(), c.endDate())));

        return new SavePersonalCommand(userId, request.job(), request.name(), request.profile_image_url(), awards, skills, companies);
    }


    /** 자기소개서 상세 응답 DTO 변환 */
    public PersonalDetailResponseDto toPersonalResponseDto(Personal personal) {

        // 자기소개서가 없으면 null 반환
        if(personal == null){
            return null;
        }

        // 수상 리스트
        List<PersonalDetailResponseDto.PersonalAwardResponse> awardList = new ArrayList<>();
        // 경력 리스트
        List<PersonalDetailResponseDto.PersonalCompanyResponse> companyList = new ArrayList<>();
        // 스킬 리스트
        List<PersonalDetailResponseDto.PersonalSkillResponse> skillList = new ArrayList<>();

        // 수상 리스트 추가
        personal.getPersonalAwards().forEach(award -> {
            awardList.add(
                new PersonalDetailResponseDto.PersonalAwardResponse(
                        award.getAwardName()));
        });

        // 스킬 리스트 추가
        personal.getPersonalSkills().forEach(skill -> {
            skillList.add(
                new PersonalDetailResponseDto.PersonalSkillResponse(
                        skill.getSkillName()));
        });

        // 경력 리스트 추가
        personal.getPersonalCompanys().forEach(company -> {
            companyList.add(
                new PersonalDetailResponseDto.PersonalCompanyResponse(
                        company.getCompanyName(),
                        company.getStartDate(),
                        company.getEndDate()));
        });


        return new PersonalDetailResponseDto(
            personal.getId(),
            personal.getName(),
            personal.getJob(),
            resourceDomain + resourceUrl + personal.getProfile_image_url(),
            awardList,
            companyList,
            skillList
        );
    }
}
