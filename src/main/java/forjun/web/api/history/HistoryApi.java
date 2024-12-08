package forjun.web.api.history;

import forjun.web.api.history.dto.HistoryRequest;
import forjun.web.api.history.dto.HistoryInfoResponse;
import forjun.web.application.history.HistoryService;
import forjun.web.application.history.dto.HistoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**히스토리 관련 제어 API*/

@RequiredArgsConstructor
@RestController
public class HistoryApi {

    private final HistoryService historyService;

    /** 히스토리 등록*/
    @PostMapping("/history")
    public ResponseEntity<HistoryInfoResponse> registerHistory(@RequestBody HistoryRequest historyRequest){

        HistoryDto historyDto =  historyService.setHistory(HistoryDto.builder()
                .project(historyRequest.getProject())
                .subject(historyRequest.getSubject())
                .category(historyRequest.getCategory())
                .description(historyRequest.getDescription())
                .skillList(historyRequest.getHistorySkill())
                .historyStartDate(historyRequest.getHistoryStartDate())
                .historyEndDate(historyRequest.getHistoryEndDate())
                .build());

        return ResponseEntity.ok(HistoryInfoResponse.fromDto(historyDto));
    }
    /** 히스토리 업데이트 */
    @PutMapping("/history")
    public ResponseEntity<HistoryInfoResponse> updateHistory(@RequestBody HistoryRequest historyRequest){

        HistoryDto historyDto =  historyService.setHistory(HistoryDto.builder()
                .historyId(historyRequest.getHistoryId())
                .project(historyRequest.getProject())
                .subject(historyRequest.getSubject())
                .category(historyRequest.getCategory())
                .description(historyRequest.getDescription())
                .skillList(historyRequest.getHistorySkill())
                .historyStartDate(historyRequest.getHistoryStartDate())
                .historyEndDate(historyRequest.getHistoryEndDate())
                .build());

        return ResponseEntity.ok(HistoryInfoResponse.fromDto(historyDto));
    }

    /** 히스토리 상세정보 열람 */
    @GetMapping("/history/{historyId}")
    public ResponseEntity<HistoryInfoResponse> viewHistory(@PathVariable("historyId") int historyId) {
        HistoryDto historyDto = historyService.getHistory(historyId);
        return ResponseEntity.ok(HistoryInfoResponse.fromDto(historyDto));
    }

    /** 히스토리 삭제 */
    @DeleteMapping("/history/{historyId}")
    public ResponseEntity<Integer> deleteHistory(@PathVariable int historyId) {
        historyService.deleteHistory(historyId);
        return ResponseEntity.ok(historyId);
    }
}
