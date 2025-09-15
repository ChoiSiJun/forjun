package forjun.web.module.personal.domain;

import forjun.web.module.personal.domain.value.PersonalAward;
import forjun.web.module.personal.domain.value.PersonalCompany;
import forjun.web.module.personal.domain.value.PersonalSkill;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class Personal {

    private Long id;
    private String userId;
    private String job;
    private String name;
    private String profile_image_url;

    List<PersonalAward> personalAwards;
    List<PersonalCompany> personalCompanys;
    List<PersonalSkill> personalSkills;

}
