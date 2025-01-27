package forjun.web.module.personalstatement.infrastructure.adapter;

import forjun.web.exception.infrastructure.JpaException;
import forjun.web.module.personalstatement.application.port.PersonalStatementPort;
import forjun.web.module.personalstatement.domain.PersonalStatement;
import forjun.web.module.personalstatement.infrastructure.repository.jpa.PersonalStatementRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PersonalStatementJpaAdapter implements PersonalStatementPort {

    private final PersonalStatementRepo personalStatementRepo;

    @Override
    public void savePersonalStatement(PersonalStatement personalStatement) {
        try {
            personalStatementRepo.save(personalStatement.toEntity());
        }catch (Exception e) {
            throw new JpaException("자기소개서 수정에 실패하였습니다.","PersonalStatement","insert or Update",e.getCause());
        }
    }

    @Override
    public List<PersonalStatement> getPersonalStatementList() {
        try {
            return personalStatementRepo.findAll().stream()
                    .map(PersonalStatement::fromEntity)
                    .collect(Collectors.toList());
        }catch (Exception e) {
            throw new JpaException("자기소개서 가져오기에 실패하였습니다","PersonalStatement","get",e.getCause());
        }
    }
}
