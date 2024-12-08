package forjun.web.application.user.dto;

import forjun.web.domain.user.UserEntity;
import forjun.web.util.EncryptionUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.SneakyThrows;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter
@AllArgsConstructor
@Builder
public class UserDto {

    private String userId;
    private String password;
    private String userName;
    private String email;
    private String authority;

    @SneakyThrows
    public UserEntity toEntity(){
        return UserEntity.builder()
                .userId(this.userId)
                .userName(this.userName)
                .password(this.password)
                .email(EncryptionUtil.encrypt(this.email))
                .build();
    }

    @SneakyThrows
    public static UserDto fromEntity(UserEntity userEntity) {
        return UserDto.builder()
                .userId(userEntity.getUserId())
                .userName(userEntity.getUserName())
                .password(userEntity.getPassword())
                .email(EncryptionUtil.decrypt(userEntity.getEmail()))
                .build();
    }

    //패스워드 암호화
    public void decryptPassword(){
        if(this.password == null || this.password.isEmpty()){
            throw new RuntimeException("Password is empty");
        }
        this.password = new BCryptPasswordEncoder().encode(this.password);
    }
}
