package forjun.web.domain.user;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<UserEntity,String> {

    //이름으로 이용자 리스트검색
    List<UserEntity> findByUserName(String username);
}
