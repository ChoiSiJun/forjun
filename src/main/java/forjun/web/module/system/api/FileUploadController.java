package forjun.web.module.system.api;

import forjun.web.module.system.api.dto.UploadedFileResponse;
import forjun.web.module.system.api.mapper.FileUploadApiMapper;
import forjun.web.module.system.application.port.in.UploadUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/system/files")
@RequiredArgsConstructor
public class FileUploadController {

    private final UploadUsecase uploadUsecase;

    private final FileUploadApiMapper fileUploadApiMapper;

    @PostMapping()
    public ResponseEntity<UploadedFileResponse> upload(
            @RequestParam("file") MultipartFile file) {

        return ResponseEntity.ok(
                fileUploadApiMapper.toUploadedFileResponse(uploadUsecase.uploadFile(file))
        );
    }

    @DeleteMapping("/{fileId}")
    public ResponseEntity<Void> delete(@PathVariable String fileId) {
        uploadUsecase.deleteFile(fileId);
        return ResponseEntity.ok().build();
    }
}