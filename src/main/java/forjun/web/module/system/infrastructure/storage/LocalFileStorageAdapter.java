package forjun.web.module.system.infrastructure.storage;

import forjun.web.module.system.application.port.out.FileStoragePort;
import forjun.web.module.system.domain.UploadFile;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
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

@Component
@Slf4j
public class LocalFileStorageAdapter implements FileStoragePort {

    private final String localUploadDir;
    private final String baseUrl;

    /**
     * @param localUploadDir application.yml에서 주입받은 로컬 업로드 디렉토리 경로
     * @param baseUrl application.yml에서 주입받은 파일 접근 베이스 URL
     */
    public LocalFileStorageAdapter(
            @Value("${file.upload.local-dir}") String localUploadDir,
            @Value("${file.upload.base-url}") String baseUrl) {

        this.localUploadDir = localUploadDir;
        this.baseUrl = baseUrl;

        // 💡 1. 디렉토리 검사 및 생성 로직 추가
        if (StringUtils.hasText(localUploadDir)) {
            Path uploadPath = Paths.get(localUploadDir);
            if (!Files.exists(uploadPath)) {
                try {
                    Files.createDirectories(uploadPath); // 디렉토리 구조가 여러 단계여도 안전하게 생성
                    log.info("로컬 업로드 디렉토리 생성 완료: {}", localUploadDir);
                } catch (IOException e) {
                    log.error("로컬 업로드 디렉토리 생성 실패", e);
                    // 초기화 실패는 애플리케이션 시작을 막아야 함
                    throw new RuntimeException("파일 저장소 초기화 실패: 디렉토리를 생성할 수 없습니다.", e);
                }
            }
        } else {
            log.warn("파일 저장소: 'file.upload.local-dir' 설정값이 비어있습니다. 실제 파일 저장 시 오류가 발생할 수 있습니다.");
            throw new RuntimeException("파일 저장소 경로(local-dir)가 설정되지 않았습니다.");
        }
    }

    @Override
    public UploadFile save(MultipartFile file) {

        String originalFilename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        String fileId = UUID.randomUUID().toString();
        String fileExtension = getFileExtension(originalFilename);
        String storedFileName = fileId + "." + fileExtension;

        Path targetLocation = Paths.get(localUploadDir).resolve(storedFileName);
        LocalDateTime uploadDate = LocalDateTime.now();

        try {
            Files.copy(file.getInputStream(), targetLocation);

            String fileUrl = baseUrl + storedFileName;

            return UploadFile.builder()
                    .fileId(fileId)
                    .originalName(originalFilename)
                    .storedPath(targetLocation.toString())
                    .url(fileUrl)
                    .size(file.getSize())
                    .contentType(file.getContentType())
                    .uploadAt(uploadDate)
                    .hashData(DigestUtils.sha256Hex(file.getInputStream()))
                    .build();

        } catch (IOException e) {
            log.error("로컬 파일 저장 실패: {}", originalFilename, e);
            // 인프라 예외를 런타임 예외로 변환
            throw new RuntimeException("파일을 로컬 시스템에 저장하는 데 실패했습니다.", e);
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
                log.warn("로컬 파일 삭제 실패: 파일을 찾을 수 없음. 경로: {}", storedPath);
            }
        } catch (IOException e) {
            log.error("로컬 파일 삭제 중 IO 오류 발생. 경로: {}", storedPath, e);
            // 인프라 예외를 런타임 예외로 변환
            throw new RuntimeException("로컬 파일 시스템에서 파일 삭제 실패했습니다.", e);
        }
    }

    // 파일 확장자를 추출하는 헬퍼 함수
    private String getFileExtension(String originalName) {
        int lastDot = originalName.lastIndexOf('.');
        return (lastDot == -1) ? "" : originalName.substring(lastDot + 1);
    }
}
