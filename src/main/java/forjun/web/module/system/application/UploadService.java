package forjun.web.module.system.application;

import forjun.web.module.system.application.port.in.UploadUsecase;
import forjun.web.module.system.application.port.out.FileStoragePort;
import forjun.web.module.system.domain.UploadedFile;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Transactional
@RequiredArgsConstructor

public class UploadService implements UploadUsecase {

    private final FileStoragePort fileStoragePort;


    @Override
    public UploadedFile uploadFile(MultipartFile multipartFile) {

        // 1. 파일 유효성 검사 (공통 로직)
        if (multipartFile.isEmpty()) {
            throw new IllegalArgumentException("파일이 비어있습니다.");
        }

        // (필요 시 파일 크기 제한, 악성 파일 체크 로직 추가)

        try {
            // 2. Secondary Port를 호출하여 실제 저장을 위임
            UploadedFile uploadedFile = fileStoragePort.save(multipartFile);

            // 3. (필요하다면) DB에 UploadedFile의 메타데이터 저장 (URL, ID, originalName 등)

            return uploadedFile;
        } catch (IOException e) {
            throw new RuntimeException("파일 저장 중 오류가 발생했습니다.", e);
        }
    }
}
