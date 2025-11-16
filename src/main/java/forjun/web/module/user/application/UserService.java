package forjun.web.module.user.application;

import forjun.web.exception.AppException;
import forjun.web.exception.ErrorCode;
import forjun.web.module.user.application.port.in.UserQuery;
import forjun.web.module.user.application.port.in.UserUsecase;
import forjun.web.module.user.application.port.out.UserJpaPort;
import forjun.web.module.user.domain.User;
import forjun.web.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements UserQuery, UserUsecase {

    private final UserJpaPort userJpaPort;
    private final JwtUtil jwtUtil;

    //유저 저장
    @Override
    public void saveUser(User user){

        //패스워드 암호화.
        user.decryptPassword();

        //기존 가입정보 있는지 확인.
        if(userJpaPort.existsByUserId(user.getUserId())){
            throw AppException.of(ErrorCode.USER_DUPLICATE, user.getUserId());
        }

        //유저 저장
        userJpaPort.saveUser(user);
    }

    @Override
    public void deleteUser(Long id) {
        userJpaPort.deleteUser(id);
    }

    @Override
    public void updateUser(User user) {

        //기존 가입정보 있는지 확인.
        if(!userJpaPort.existsById(user.getId())){
            throw AppException.of(ErrorCode.USER_NOT_FOUND, user.getId());
        }

        //유저 저장
        userJpaPort.saveUser(user);
    }

    @Override
    public User getUser(Long id) {
        User user = userJpaPort.getUser(id);
        if(user == null){
            throw AppException.of(ErrorCode.USER_NOT_FOUND, id);
        }
        return userJpaPort.getUser(id);
    }

    @Override
    public User getUserByUserId(String userId) {
        return userJpaPort.getUserByUserId(userId);
    }

    @Override
    public String authentication(User user) {

        String input_id = user.getUserId();
        String input_password = user.getPassword();
        user = userJpaPort.getUserByUserId(user.getUserId());
        if (user == null) {
            throw AppException.of(ErrorCode.ID_NOT_FOUND, input_id);
        }

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if(!passwordEncoder.matches(input_password, user.getPassword())){
            throw AppException.of(ErrorCode.PASSWORD_NOT_MATCH, input_id);
        }
        return jwtUtil.generateToken(user.getUserId() , user.getUserName() , user.getAuthority());
    }

    @Override
    public boolean existsByUserId(String userid) {
        return userJpaPort.existsByUserId(userid);
    }
}
