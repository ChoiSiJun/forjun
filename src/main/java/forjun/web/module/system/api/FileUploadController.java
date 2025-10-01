package forjun.web.module.system.api;

import forjun.web.module.system.api.dto.UploadedFileResponse;
import forjun.web.module.system.api.mapper.FileUploadApiMapper;
import forjun.web.module.system.application.port.in.UploadUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/system/files")
@RequiredArgsConstructor
public class FileUploadController {

    private final UploadUsecase uploadUsecase;

    private final FileUploadApiMapper fileUploadApiMapper;

    @PostMapping("/upload")
    public ResponseEntity<UploadedFileResponse> upload(
            @RequestParam("file") MultipartFile file) {

        return ResponseEntity.ok(
                fileUploadApiMapper.toUploadedFileResponse(uploadUsecase.uploadFile(file))
        );
    }
}