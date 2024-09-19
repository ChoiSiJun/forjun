package forjun.web.api.user.dto;

import forjun.web.application.user.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserInfoResponse {

    private String userId;

    private String userName;

    private String email;

    public static UserInfoResponse fromDto(UserDto userDTO){
        return new UserInfoResponse(userDTO.getUserId(), userDTO.getUserName(), userDTO.getEmail());
    }
}
