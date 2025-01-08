package forjun.web.module.user.api.dto;


import forjun.web.module.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserInfoResponse {

    private String userId;

    private String userName;

    private String email;

    public static UserInfoResponse fromDto(User user){
        return new UserInfoResponse(user.getUserId(), user.getUserName(), user.getEmail());
    }
}
