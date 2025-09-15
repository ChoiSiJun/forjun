package forjun.web.module.personal.domain.value;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PersonalCompany {
    private String company_name;
    private String startDate;
    private String endDate;
}
