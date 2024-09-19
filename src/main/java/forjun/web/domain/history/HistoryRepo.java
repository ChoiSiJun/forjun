package forjun.web.domain.history;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HistoryRepo extends JpaRepository<HistoryEntity, Integer> {}

