package forjun.web.module.personalstatement.api.dto;

import forjun.web.module.personalstatement.domain.PersonalStatement;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class PersonalStatementInfoResponse {

    private String type;
    private String title;
    private String data;

    public static PersonalStatementInfoResponse fromDomain(PersonalStatement personalStatement) {
        return new PersonalStatementInfoResponse(
                personalStatement.getType(),
                personalStatement.getTitle(),
                personalStatement.getData()
        );
    }
}
