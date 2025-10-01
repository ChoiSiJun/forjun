package forjun.web.module.system.infrastructure.storage;

import forjun.web.module.system.application.port.out.FileStoragePort;
import forjun.web.module.system.domain.UploadFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

// 💡 추상화된 런타임 예외 클래스가 필요하다고 가정하고, RuntimeException으로 대체합니다.
// 실제로는 FileStorageException extends RuntimeException을 정의해야 합니다.

@Component
@Slf4j
public class LocalFileStorageAdapter implements FileStoragePort {

    @Value("${file.upload.local-dir}")
    private String localUploadDir;

    @Value("${file.upload.base-url}")
    private String baseUrl;

    @Override
    public UploadFile save(MultipartFile file) { // 💡 IOException 제거

        //파일 원본이름
        String originalFilename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        //파일 ID
        String fileId = UUID.randomUUID().toString();

        //파일 확장자
        String fileExtension = getFileExtension(originalFilename);

        //실제 저장 파일 이름
        String storedFileName = fileId + "." + fileExtension;

        //저장 경로 설정
        Path targetLocation = Paths.get(localUploadDir).resolve(storedFileName);

        //파일 업로드 시간
        LocalDateTime uploadDate = LocalDateTime.now();

        try {
            //파일 저장 (실제 디스크 쓰기)
            Files.copy(file.getInputStream(), targetLocation);

            //접근 URL 및 저장 경로 설정
            String fileUrl = baseUrl + storedFileName; // 다운로드 엔드포인트와 연결된 URL

            return UploadFile.builder()
                    .fileId(fileId)
                    .originalName(originalFilename)
                    .storedPath(targetLocation.toString())
                    .url(fileUrl)
                    .size(file.getSize())
                    .contentType(file.getContentType())
                    .uploadAt(uploadDate)
                    .build();

        } catch (IOException e) {
            log.error("로컬 파일 저장 실패: {}", originalFilename, e);
            throw new RuntimeException("파일 저장소 시스템 오류 발생", e);
        }
    }

    @Override
    public void delete(String storedPath) {

        Path filePath = Paths.get(storedPath);
        try {
            boolean deleted = Files.deleteIfExists(filePath);
            if (deleted) {
                log.info("로컬 파일 삭제 성공. 경로: {}", storedPath);
            } else {
                // 파일이 이미 삭제되었거나 경로에 존재하지 않을 경우
                log.warn("로컬 파일 삭제 실패: 파일을 찾을 수 없음.  경로: {}", storedPath);
            }
        } catch (IOException e) {
            log.error("로컬 파일 삭제 중 IO 오류 발생. 경로: {}", storedPath, e);
            throw new RuntimeException("로컬 파일 시스템에서 파일 삭제 실패: " + storedPath, e);
        }
    }

    // 파일 확장자를 추출하는 헬퍼 함수
    private String getFileExtension(String originalName) {
        int lastDot = originalName.lastIndexOf('.');
        return (lastDot == -1) ? "" : originalName.substring(lastDot + 1);
    }
}
