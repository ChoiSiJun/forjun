package forjun.web.module.personal.api.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class SavePersonalRequestDto {
    
    //고유 아이디
    private Long id;

    //직업
    private String job;
    
    //이름
    private String name;
    
    //프로필 사진
    private String profile_image_url;


    private List<PersonalAwardRequest> awards;
    private List<PersonalCompanyRequest> companies;
    private List<PersonalSkillRequest> skills;

    @Getter
    @Setter
    public static class PersonalAwardRequest {
        private String awardName;
    }

    @Getter
    @Setter
    public static class PersonalCompanyRequest {
        private String companyName;
        private String startDate;
        private String endDate;
    }

    @Getter
    @Setter
    public static class PersonalSkillRequest {
        private String skillName;
    }
}
