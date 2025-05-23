package forjun.web.module.user.api.dto;

import forjun.web.module.user.domain.User;
import forjun.web.util.JwtUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserLoginResponse
{
    private String token;

    public static UserLoginResponse fromDomain(User user , JwtUtil jwtUtil){
        return new UserLoginResponse(jwtUtil.generateToken(user.getUserId(),user.getUserName(),user.getAuthority()));
    }
}
