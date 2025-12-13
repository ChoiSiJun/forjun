package forjun.web.module.personal.application;

import forjun.web.module.personal.application.port.in.PersonalQuery;
import forjun.web.module.personal.application.port.in.PersonalUseCase;
import forjun.web.module.personal.application.port.in.dto.GetPersonalQuery;
import forjun.web.module.personal.application.port.in.dto.SavePersonalCommand;
import forjun.web.module.personal.domain.Personal;
import forjun.web.module.personal.infrastructure.jpa.PersonalJpaAdapater;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
@Transactional
public class PersonalService implements PersonalUseCase , PersonalQuery {

    //personal Jpa 어댑터
    private final PersonalJpaAdapater personalJpaAdapater;
    
    @Override
    public void savePersonal(SavePersonalCommand command) {

        // 자기소개서 도메인 생성
        personalJpaAdapater.savePersonal(PersonalFactory.createPersonal(command));
        
    }

    @Override
    public Personal getPersonal(GetPersonalQuery query) {
        return personalJpaAdapater.getPersonal(query.userId()).orElse(null);
    }
}
