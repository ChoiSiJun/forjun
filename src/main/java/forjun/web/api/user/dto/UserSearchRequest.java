package forjun.web.api.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSearchRequest {

    //아이디
    private String userId;

    //이름
    private String userName;
}
