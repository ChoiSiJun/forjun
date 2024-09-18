package forjun.web.api.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserJoinRequest {

    private String userName;
    private String password;
    private String email;

}
