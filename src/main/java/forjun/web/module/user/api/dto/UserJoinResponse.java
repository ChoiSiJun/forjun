package forjun.web.module.user.api.dto;

import forjun.web.module.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserJoinResponse {

    private String userId;
    private String userName;
    private String email;

    public static UserJoinResponse fromDomain(User user){
        return new UserJoinResponse(user.getUserId(), user.getUserName(), user.getEmail());
    }
}
