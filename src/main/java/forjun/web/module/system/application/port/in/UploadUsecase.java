package forjun.web.module.system.application.port.in;

import forjun.web.module.system.domain.UploadedFile;
import org.springframework.web.multipart.MultipartFile;

public interface UploadUsecase {
    
    //파일 업로드 서비스
    public UploadedFile uploadFile(MultipartFile multipartFile);
}
