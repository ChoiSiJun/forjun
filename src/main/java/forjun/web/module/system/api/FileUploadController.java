package forjun.web.module.system.api;

import forjun.web.module.system.application.port.in.UploadUsecase;
import forjun.web.module.system.domain.UploadedFile;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/system/files") // 💡 엔드포인트를 'files'로 변경
@RequiredArgsConstructor
public class FileUploadController {

    private final UploadUsecase uploadUsecase;

    @PostMapping("/upload")
    public ResponseEntity<UploadedFileResponse> upload(
            @RequestParam("file") MultipartFile file) {

        // Port 호출 (UploadedFile 모델 객체를 받음)
        UploadedFile uploadedFile = uploadUsecase.uploadFile(file);

        // 클라이언트에게 필요한 정보만 담은 응답 DTO로 변환하여 반환
        return ResponseEntity.ok(UploadedFileResponse.from(uploadedFile));
    }

    // 응답 DTO (클라이언트에게 모든 내부 정보를 노출할 필요는 없음)
    public record UploadedFileResponse(String fileId, String url, String originalName) {
        public static UploadedFileResponse from(UploadedFile file) {
            return new UploadedFileResponse(file.getFileId(), file.getUrl(), file.getOriginalName());
        }
    }
}