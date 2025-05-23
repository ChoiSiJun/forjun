package forjun.web.module.personalstatement.application;

import forjun.web.module.personalstatement.application.port.PersonalStatementPort;
import forjun.web.module.personalstatement.domain.PersonalStatement;
import forjun.web.module.personalstatement.infrastructure.adapter.PersonalStatementJpaAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonalStatementService {

    private final PersonalStatementJpaAdapter personalStatementJpaAdapter;

    //자기소개서 변경
    public void savePersonalStatement(PersonalStatement personalStatement){
        personalStatementJpaAdapter.savePersonalStatement(personalStatement);
    }

    //자기소개서 가져오기
    public List<PersonalStatement> getPersonalStatementList(){
        return personalStatementJpaAdapter.getPersonalStatementList();
    }

    //자기소개서 삭제
    public void deletePersonalStatement(int id){
        personalStatementJpaAdapter.deletePersonalStatement(id);
    }
}
