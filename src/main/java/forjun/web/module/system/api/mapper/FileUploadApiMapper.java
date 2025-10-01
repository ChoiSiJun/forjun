package forjun.web.module.system.api.mapper;

import forjun.web.module.system.api.dto.UploadedFileResponse;
import forjun.web.module.system.domain.UploadFile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FileUploadApiMapper {
    UploadedFileResponse toUploadedFileResponse(UploadFile uploadFile);
}
