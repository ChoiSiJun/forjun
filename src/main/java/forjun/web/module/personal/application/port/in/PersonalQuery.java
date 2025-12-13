package forjun.web.module.personal.application.port.in;

import forjun.web.module.personal.application.port.in.dto.GetPersonalQuery;
import forjun.web.module.personal.domain.Personal;

public interface PersonalQuery {
    public Personal getPersonal(GetPersonalQuery query);
}
