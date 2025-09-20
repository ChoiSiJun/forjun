package forjun.web.module.personal.application.port.out;

import forjun.web.module.personal.domain.Personal;

import java.util.Optional;

public interface PersonalJpaPort {

    public void savePersonal(Personal personal);

    public Optional<Personal> getPersonal(Long id);
}
