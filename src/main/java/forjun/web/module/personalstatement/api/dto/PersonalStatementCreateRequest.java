package forjun.web.module.personalstatement.api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonalStatementCreateRequest {

    @NotNull(message = "타입값이 없습니다.")
    private String type;

    private String label;

    @NotNull(message = "내용이 없습니다.")
    private String data;
}
