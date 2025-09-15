package forjun.web.module.personal.infrastructure.jpa.repository;

import forjun.web.module.personal.infrastructure.jpa.entity.PersonalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalRepository extends JpaRepository<PersonalEntity , Long> {
}
