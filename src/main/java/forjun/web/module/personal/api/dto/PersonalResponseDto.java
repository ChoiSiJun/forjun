package forjun.web.module.personal.api.dto;


import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class PersonalResponseDto {

    //고유 아이디
    private Long id;

    //직업
    private String job;

    //이름
    private String name;

    //프로필 사진
    private String profile_image_url;


    private List<PersonalAwardResponse> awards;
    private List<PersonalCompanyResponse> companies;
    private List<PersonalSkillResponse> skills;

    @Builder
    @Getter
    public static class PersonalAwardResponse {
        private String awardName;
    }

    @Builder
    @Getter
    public static class PersonalCompanyResponse {
        private String companyName;
        private String startDate;
        private String endDate;
    }

    @Builder
    @Getter
    public static class PersonalSkillResponse {
        private String skillName;
    }
}
