package forjun.web.module.personal.application.port.out;

import forjun.web.module.personal.domain.Personal;

public interface PersonalJpaPort {

    public void savePersonal(Personal personal);
}
