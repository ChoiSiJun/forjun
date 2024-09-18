package forjun.web.application.user.dto;

import forjun.web.domain.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDto {

    private String userId;
    private String password;
    private String userName;
    private String email;

    public UserEntity toEntity() {
        return UserEntity.builder()
                .userId(this.getUserId())
                .userName(this.getUserName())
                .password(this.getPassword())
                .email(this.getEmail())
                .build();
    }

    public static UserDto fromEntity(UserEntity userEntity){
        return new UserDto(userEntity.getUserId(), userEntity.getUserName(), userEntity.getPassword(), userEntity.getEmail());
    }
}
