package forjun.web.module.personal.application;

import forjun.web.exception.AppException;
import forjun.web.exception.ErrorCode;
import forjun.web.module.personal.application.port.in.PersonalQuery;
import forjun.web.module.personal.application.port.in.PersonalUseCase;
import forjun.web.module.personal.domain.Personal;
import forjun.web.module.personal.infrastructure.jpa.PersonalJpaAdapater;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class PersonalService implements PersonalUseCase , PersonalQuery {

    //personal Jpa 어댑터
    private final PersonalJpaAdapater personalJpaAdapater;
    
    @Override
    public void savePersonal(Personal personal) {

        //Personal 어댑터 호출
        personalJpaAdapater.savePersonal(personal);
        
    }

    @Override
    public Personal getPersonal(String userId) {
        return personalJpaAdapater.getPersonal(userId).orElse(null);
    }
}
