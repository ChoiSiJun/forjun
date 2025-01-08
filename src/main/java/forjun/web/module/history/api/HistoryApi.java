package forjun.web.module.history.api;


import forjun.web.module.history.api.dto.HistoryInfoResponse;
import forjun.web.module.history.api.dto.HistoryRequest;
import forjun.web.module.history.application.HistoryService;
import forjun.web.module.history.domain.History;
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
    public ResponseEntity<Integer> registerHistory(@RequestBody HistoryRequest historyRequest){

        historyService.saveHistory(History.builder()
                .project(historyRequest.getProject())
                .subject(historyRequest.getSubject())
                .category(historyRequest.getCategory())
                .description(historyRequest.getDescription())
                .skillList(historyRequest.getHistorySkill())
                .historyStartDate(historyRequest.getHistoryStartDate())
                .historyEndDate(historyRequest.getHistoryEndDate())
                .build());

        return ResponseEntity.ok(historyRequest.getHistoryId());
    }
    /** 히스토리 업데이트 */
    @PutMapping("/history")
    public ResponseEntity<Integer> updateHistory(@RequestBody HistoryRequest historyRequest){

        historyService.saveHistory(History.builder()
                .historyId(historyRequest.getHistoryId())
                .project(historyRequest.getProject())
                .subject(historyRequest.getSubject())
                .category(historyRequest.getCategory())
                .description(historyRequest.getDescription())
                .skillList(historyRequest.getHistorySkill())
                .historyStartDate(historyRequest.getHistoryStartDate())
                .historyEndDate(historyRequest.getHistoryEndDate())
                .build());

        return ResponseEntity.ok(historyRequest.getHistoryId());
    }

    /** 히스토리 상세정보 열람 */
    @GetMapping("/history/{historyId}")
    public ResponseEntity<HistoryInfoResponse> viewHistory(@PathVariable("historyId") int historyId) {
        History history = historyService.getHistory(historyId);
        return ResponseEntity.ok(HistoryInfoResponse.fromDto(history));
    }

    /** 히스토리 삭제 */
    @DeleteMapping("/history/{historyId}")
    public ResponseEntity<Integer> deleteHistory(@PathVariable int historyId) {
        historyService.deleteHistory(historyId);
        return ResponseEntity.ok(historyId);
    }
}
