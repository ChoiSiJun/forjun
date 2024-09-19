package forjun.web.application.user;

import forjun.web.api.user.dto.UserJoinRequest;
import forjun.web.application.user.dto.UserDto;
import forjun.web.domain.user.UserEntity;

import forjun.web.domain.user.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    //유저 저장 & 수정
    public UserDto saveUser(UserDto userDto){

       UserEntity userEntity = userRepo.findById(userDto.getUserId())
                        .map(user -> {
                                    user.updateUser()
                                            .userName(userDto.getUserName())
                                            .email(userDto.getEmail())
                                            .build();
                                    return user;
                                }
                        ).orElse(userDto.toEntity());

        userRepo.save(userDto.toEntity());
        return userDto;
    }

    //유저 정보
    public UserDto viewUser(String userid){
        return UserDto.fromEntity(userRepo.findById(userid).orElseThrow( ()-> new EntityNotFoundException("User with id " + userid + " not found") ));
    }

    //유저 리스트 ( 이름 )
    public List<UserDto> SearchUserList(String keyword){
        return userRepo.findByUserName(keyword).stream()
                .map(UserDto::fromEntity)
                .collect(Collectors.toList());
    }
}
