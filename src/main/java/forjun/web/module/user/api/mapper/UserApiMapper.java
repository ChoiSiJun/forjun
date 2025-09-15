package forjun.web.module.user.api.mapper;

import forjun.web.module.user.api.dto.UserJoinRequest;
import forjun.web.module.user.api.dto.UserLoginRequest;
import forjun.web.module.user.domain.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserApiMapper {

    //유저 회원가입
    User toDomain(UserJoinRequest request);

    //유저 로그인
    User toDomain(UserLoginRequest request);
}
