package forjun.web.module.personal.infrastructure.jpa;

import forjun.web.module.personal.application.port.out.PersonalJpaPort;
import forjun.web.module.personal.domain.Personal;
import forjun.web.module.personal.infrastructure.jpa.entity.PersonalEntity;
import forjun.web.module.personal.infrastructure.jpa.mapper.PersonalEntityMapper;
import forjun.web.module.personal.infrastructure.jpa.repository.PersonalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PersonalJpaAdapater implements PersonalJpaPort {

    private final PersonalEntityMapper personalEntityMapper;
    private final PersonalRepository personalRepository;

    @Override
    public void savePersonal(Personal personal) {
        PersonalEntity personalEntity = personalEntityMapper.toEntity(personal);
        personalRepository.save(personalEntity);
    }

    @Override
    public Optional<Personal> getPersonal(Long id) {
        return personalRepository.findById(id).map(
                personalEntityMapper::toDomain
        );
    }
}
