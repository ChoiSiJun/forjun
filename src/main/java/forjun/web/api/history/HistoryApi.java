package forjun.web.api.history;

import forjun.web.api.history.dto.HistoryInfoResponse;
import forjun.web.application.history.HistoryService;
import forjun.web.application.history.dto.HistoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class HistoryApi {

    private final HistoryService historyService;

    /** 히스토리 상세정보 열람 */
    @GetMapping("/history/{historyId}")
    public ResponseEntity<HistoryInfoResponse> getHistory(@PathVariable("historyId") int historyId) {
        HistoryDto historyDto = historyService.getHistory(historyId);
        return ResponseEntity.ok(HistoryInfoResponse.fromDto(historyDto));
    }

}
