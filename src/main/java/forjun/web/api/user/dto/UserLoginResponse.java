package forjun.web.api.user.dto;

import forjun.web.application.user.dto.UserDto;
import forjun.web.util.JwtUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserLoginResponse
{
    private String token;

    public static UserLoginResponse fromDto(UserDto dto , JwtUtil jwtUtil){
        return new UserLoginResponse(jwtUtil.generateToken(dto.getUserId()));
    }
}
