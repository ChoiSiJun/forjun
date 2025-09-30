package forjun.web.module.system.infrastructure;

import forjun.web.module.system.application.port.out.FileStoragePort;
import forjun.web.module.system.domain.UploadedFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Component // Secondary Adapter로 Spring Bean 등록
@Slf4j
public class LocalFileStorageAdapter implements FileStoragePort {

    // 💡 application.yml에서 설정 값 주입
    @Value("${file.upload.local-dir}")
    private String localUploadDir;

    @Value("${file.upload.base-url}")
    private String baseUrl;

    // 💡 생성자 시점에 업로드 디렉토리가 없으면 생성
    public LocalFileStorageAdapter() {
        Path uploadPath = Paths.get(localUploadDir);
        if (!Files.exists(uploadPath)) {
            try {
                Files.createDirectories(uploadPath);
                log.info("로컬 업로드 디렉토리 생성: {}", localUploadDir);
            } catch (IOException e) {
                log.error("로컬 업로드 디렉토리 생성 실패", e);
                throw new RuntimeException("파일 저장소 초기화 실패", e);
            }
        }
    }

    @Override
    public UploadedFile save(MultipartFile file) throws IOException {
        String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());
        // 1. 파일 ID 및 저장될 파일명 생성
        String fileId = UUID.randomUUID().toString();
        String fileExtension = getFileExtension(originalFilename);
        String storedFileName = fileId + "." + fileExtension;

        // 2. 저장 경로 설정
        Path targetLocation = Paths.get(localUploadDir).resolve(storedFileName);

        try {
            // 3. 파일 저장 (실제 디스크 쓰기)
            Files.copy(file.getInputStream(), targetLocation);

            // 4. 접근 URL 및 저장 경로 설정
            String fileUrl = baseUrl + storedFileName; // 다운로드 엔드포인트와 연결된 URL

            return UploadedFile.builder()
                    .fileId(fileId)
                    .originalName(originalFilename)
                    .storedPath(targetLocation.toString())
                    .url(fileUrl)
                    .size(file.getSize())
                    .contentType(file.getContentType())
                    .build();

        } catch (IOException e) {
            log.error("로컬 파일 저장 실패: {}", originalFilename, e);
            throw e;
        }
    }

    @Override
    public void delete(String fileId) {
        // 실제 삭제 로직 (fileId로 DB에서 storedPath를 조회 후 삭제)
        // 💡 현재는 fileId로 로컬 경로를 직접 유추하기 어렵기 때문에, 실제 DB 연동이 필요합니다.
        log.warn("LocalFileStorageAdapter: 파일 삭제 기능은 DB 연동 후 구현 필요. 요청 ID: {}", fileId);

        // (예시) 파일 경로를 직접 알고 있을 경우의 삭제 로직
        // try {
        //     Path filePath = Paths.get(localUploadDir).resolve("경로를 찾아야 할 파일명");
        //     Files.deleteIfExists(filePath);
        // } catch (IOException e) { /* ... */ }
    }

    // 파일 확장자를 추출하는 헬퍼 함수
    private String getFileExtension(String originalName) {
        int lastDot = originalName.lastIndexOf('.');
        return (lastDot == -1) ? "" : originalName.substring(lastDot + 1);
    }
}