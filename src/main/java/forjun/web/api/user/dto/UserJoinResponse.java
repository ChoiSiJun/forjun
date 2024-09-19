package forjun.web.api.user.dto;

import forjun.web.application.user.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserJoinResponse {

    private String userId;
    private String userName;
    private String email;

    public static UserJoinResponse fromDto(UserDto userDTO){
        return new UserJoinResponse(userDTO.getUserId(), userDTO.getUserName(), userDTO.getEmail());
    }
}
