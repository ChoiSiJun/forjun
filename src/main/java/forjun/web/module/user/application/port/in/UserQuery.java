package forjun.web.module.user.application.port.in;
import forjun.web.module.user.domain.User;

public interface UserQuery {
    User getUser(Long id);

    User getUserByUserId(String userId);

    String authentication(User user);

    boolean existsByUserId(String userId);
}
