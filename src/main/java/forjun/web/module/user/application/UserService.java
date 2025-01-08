package forjun.web.module.user.application;


import forjun.web.exception.application.authentication.NotMatchPasswordException;
import forjun.web.exception.application.user.UserDuplicateException;
import forjun.web.exception.application.user.UserNotFoundException;
import forjun.web.module.user.domain.User;
import forjun.web.module.user.infrastructure.adapter.UserAdapter;
import forjun.web.module.user.infrastructure.repository.jpa.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserAdapter userAdapter;

    //유저 저장
    public User saveUser(User user){

            //패스워드 암호화.
            user.decryptPassword();

            //기존 가입정보 있는지 확인.
            if(userAdapter.duplicateUserCheck(user.getUserId())){
                throw new UserDuplicateException();
            }
            
            //유저 저장
            userAdapter.saveUser(user);

        return user;
    }

    //유저 정보
    public User viewUser(String userid){

        User user = userAdapter.getUser(userid);
        if(user==null){
            throw new UserNotFoundException();
        }

        return user;
    }

    //유저 리스트 ( 이름 )
    @SneakyThrows
    public List<User> SearchUserList(String keyword){

        List<User> userList = userAdapter.searchUserList(keyword);
        if(userList.isEmpty()){
            throw new UserNotFoundException();
        }

        return userList;
    }
    
    //유저 아이디 중복체크
    public Boolean DuplicateUser(String userid){

        if(userAdapter.duplicateUserCheck(userid)){
            throw new UserDuplicateException();
        }

        return false;
    }

    //유저 아이디 패스워드 인증
    public User Authentication(User user){

        User userResult = userAdapter.getUser(user.getUserId());
        if(userResult==null){
            throw new UserNotFoundException();
        }

        if(!new BCryptPasswordEncoder().matches(user.getPassword() , userResult.getPassword())){
            throw new NotMatchPasswordException();
        }

        return userResult;
    }
}
