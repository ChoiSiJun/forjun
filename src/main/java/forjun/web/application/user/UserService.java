package forjun.web.application.user;

import forjun.web.api.user.dto.UserLoginRequest;
import forjun.web.application.user.dto.UserDto;
import forjun.web.domain.user.UserEntity;

import forjun.web.domain.user.UserRepo;
import forjun.web.exception.authentication.NotMatchPasswordException;
import forjun.web.exception.user.UserNotCreateException;
import forjun.web.exception.user.UserNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    //유저 저장
    public UserDto saveUser(UserDto userDto){

        try {
            //패스워드 암호화.
            userDto.decryptPassword();

            //기존 가입정보 있는지 확인.
            if(userRepo.findById(userDto.getUserId()).isPresent()){
                throw new UserNotCreateException(userDto.getUserId());
            }
            userRepo.save(userDto.toEntity());

        }catch (Exception e){
            throw new UserNotCreateException(e.getMessage());
        }
        return userDto;
    }

    //유저 정보
    public UserDto viewUser(String userid){
        return UserDto.fromEntity(userRepo.findById(userid).orElseThrow( ()-> new EntityNotFoundException("User with id " + userid + " not found") ));
    }

    //유저 리스트 ( 이름 )
    @SneakyThrows
    public List<UserDto> SearchUserList(String keyword){
        return userRepo.findByUserName(keyword).stream()
                .map(UserDto::fromEntity)
                .collect(Collectors.toList());
    }
    
    //유저 아이디 중복체크
    public Boolean DuplicateUser(String userid){
        return userRepo.findById(userid).isPresent();
    }

    //유저 아이디 패스워드 인증
    public UserDto Authentication(UserDto userDto){

        UserEntity userEntity = userRepo.findById(userDto.getUserId())
                .orElseThrow(UserNotFoundException::new);

        if(!new BCryptPasswordEncoder().matches(userDto.getPassword() , userEntity.getPassword())){
            throw new NotMatchPasswordException();
        }

        return UserDto.fromEntity(userEntity);
    }
}
