package forjun.web.module.system.application.port.out;

import forjun.web.module.system.domain.UploadedFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileStoragePort {

    //파일 업로드
    UploadedFile save(MultipartFile file) throws IOException;

    //파일 삭제
    void delete(String fileId);
}
