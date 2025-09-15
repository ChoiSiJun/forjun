package forjun.web.module.personal.application.port.in;

import forjun.web.module.personal.domain.Personal;

//개인정보 생성 유스케이스
public interface PersonalUseCase {

    public void savePersonal(Personal personal);
}
