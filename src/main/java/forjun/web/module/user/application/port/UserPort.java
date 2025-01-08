package forjun.web.module.user.application.port;

import forjun.web.module.user.domain.User;

import java.util.List;

public interface UserPort {

    //유저 저장 및 수정
    public void saveUser(User user);

    //유저정보 아이디로 얻기
    public User getUser(String userId);
    
    //유저 리스트 검색
    public List<User> searchUserList(String keyword);

    //유저 아이디 여부 조회
    public Boolean duplicateUserCheck(String userId);

}
