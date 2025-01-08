package forjun.web.module.user.infrastructure.adapter;

import forjun.web.exception.infrastructure.JpaException;
import forjun.web.module.user.application.port.UserPort;
import forjun.web.module.user.domain.User;
import forjun.web.module.user.infrastructure.repository.jpa.UserEntity;
import forjun.web.module.user.infrastructure.repository.jpa.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class UserAdapter implements UserPort {

    private final UserRepo userRepo;

    @Override
    public void saveUser(User user) {
        try {
            userRepo.save(user.toEntity());
        }catch (Exception e) {
            if((user.getUserId() == null)){
                throw new JpaException("이용자 생성에 실패하였습니다.","User","insert",e.getCause());
            }else{
                throw new JpaException("이용자 수정에 실패하였습니다.","User","update",e.getCause());
            }
        }
    }

    @Override
    public User getUser(String userId) {
        try {
            Optional<UserEntity> userEntity = userRepo.findById(userId);
            return userEntity.map(User::fromEntity).orElse(null);
        } catch (Exception e) {
            // 추가적인 예외 처리 및 재포장
            throw new JpaException("이용자 조회에 실패하였습니다.", "User", "find", e.getCause());
        }
    }

    @Override
    public List<User> searchUserList(String keyword) {

        try {
            return userRepo.findByUserName(keyword).stream()
                    .map(User::fromEntity)
                    .collect(Collectors.toList());
        }catch (Exception e) {
            throw new JpaException("이용자 리스트 조회에 실패하였습니다.", "User", "find", e.getCause());
        }

    }

    @Override
    public Boolean duplicateUserCheck(String userId) {
        try {
            return userRepo.findById(userId).isPresent();
        } catch (Exception e) {
            throw new JpaException("이용자 중복 조회에 실패하였습니다.", "User", "find", e.getCause());
        }
    }
}

