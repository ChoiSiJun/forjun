package forjun.web.module.personalstatement.infrastructure.repository.jpa;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalStatementRepo extends JpaRepository<PersonalStatementEntity,Integer> {
}
