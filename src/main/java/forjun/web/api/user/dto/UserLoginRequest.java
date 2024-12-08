package forjun.web.api.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginRequest {

    @NotBlank(message = "아이디를 입력하지 않으셨습니다.")
    private String loginId;
    
    @NotBlank(message = "패스워드를 입력하지 않으셨습니다.")
    private String loginPassword;

}
