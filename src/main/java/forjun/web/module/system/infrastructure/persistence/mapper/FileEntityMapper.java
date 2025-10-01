package forjun.web.module.system.infrastructure.persistence.mapper;


import forjun.web.module.system.domain.UploadFile;
import forjun.web.module.system.infrastructure.persistence.entity.UploadFileEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FileEntityMapper {
    UploadFileEntity toEntity(UploadFile uploadFile);
}
