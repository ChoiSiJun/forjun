package forjun.web.module.personalstatement.application.port;

import forjun.web.module.personalstatement.domain.PersonalStatement;

import java.util.List;

public interface PersonalStatementPort {

    public void savePersonalStatement(PersonalStatement personalStatement);

    public List<PersonalStatement> getPersonalStatementList();
}
