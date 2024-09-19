package forjun.web.application.user.dto;

import forjun.web.domain.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
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
        return UserDto.builder()
                .userId(userEntity.getUserId())
                .userName(userEntity.getUserName())
                .password(userEntity.getPassword())
                .email(userEntity.getEmail())
                .build();
    }
}
