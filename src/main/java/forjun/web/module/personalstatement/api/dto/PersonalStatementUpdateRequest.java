package forjun.web.module.personalstatement.api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonalStatementUpdateRequest {

    @NotNull(message = "선택된 사항이 없습니다.")
    private Integer id;
    private String type;
    private String label;
    private String data;
}
