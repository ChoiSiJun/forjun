package forjun.web.module.user.application.port.in;

import forjun.web.module.user.domain.User;

public interface UserUsecase {

    //유저 저장
    public void saveUser(User user);

    //유저 삭제
    public void deleteUser(Long id);

    //유저 수정
    public void updateUser(User user);

}
