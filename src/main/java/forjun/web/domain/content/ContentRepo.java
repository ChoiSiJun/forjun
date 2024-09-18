package forjun.web.domain.content;

import forjun.web.domain.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentRepo extends JpaRepository<ContentEntity,Integer> {

}
