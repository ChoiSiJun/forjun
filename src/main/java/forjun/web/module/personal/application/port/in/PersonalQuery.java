package forjun.web.module.personal.application.port.in;

import forjun.web.module.personal.application.port.in.dto.GetPersonalQuery;
import forjun.web.module.personal.application.port.in.dto.GetPublicPersonalQuery;
import forjun.web.module.personal.domain.Personal;

/** 자기소개서 조회 인터페이스 */
public interface PersonalQuery {

    /** 자기소개서 조회 */
    public Personal getPersonal(GetPersonalQuery query);

    /** 자기소개서 공개 조회 */
    public Personal getPublicPersonal(GetPublicPersonalQuery query);
}
