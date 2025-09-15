package forjun.web.module.history.api;


import forjun.web.config.security.CustomUserDetail;
import forjun.web.module.history.api.dto.HistoryCreateRequest;
import forjun.web.module.history.api.dto.HistoryResponse;
import forjun.web.module.history.api.dto.HistoryUpdateRequest;
import forjun.web.module.history.api.mapper.HistoryApiMapper;
import forjun.web.module.history.application.port.in.HistoryQuery;
import forjun.web.module.history.application.port.in.HistoryUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**히스토리 관련 제어 API*/


@RequiredArgsConstructor
@RestController
public class HistoryApi {

    private final HistoryQuery historyQuery;
    private final HistoryUsecase historyUsecase;
    private final HistoryApiMapper historyApiMapper;

    /** 히스토리 등록*/
    @PostMapping("/history")
    public ResponseEntity<Void> registerHistory(Authentication auth , @RequestBody HistoryCreateRequest request) {
        CustomUserDetail user = (CustomUserDetail) auth.getPrincipal();
        historyUsecase.createHistory(historyApiMapper.toDomain(user.getUserId(),request));
        return ResponseEntity.ok().build();
    }
    /** 히스토리 업데이트 */
    @PutMapping("/history")
    public ResponseEntity<Void> updateHistory(Authentication auth , @RequestBody HistoryUpdateRequest request){

        CustomUserDetail user = (CustomUserDetail) auth.getPrincipal();
        historyUsecase.updateHistory(historyApiMapper.toDomain(user.getUserId() , request));
        return ResponseEntity.ok().build();
    }

    /** 히스토리 가져오기 */
    @GetMapping("/history/{historyId}")
    public ResponseEntity<HistoryResponse> getHistory(@PathVariable int historyId)
    {

        return ResponseEntity.ok(historyApiMapper.toHistoryResponse(historyQuery.getHistory(historyId)));
    }

    /** 히스토리 리스트 가져오기*/
    @GetMapping("/histories")
    public ResponseEntity<List<HistoryResponse>> histories(Authentication auth, @RequestParam String category)
    {
        CustomUserDetail user = (CustomUserDetail) auth.getPrincipal();
        return ResponseEntity.ok(historyApiMapper.toHistoryReponseList(historyQuery.getHistorys(category,user.getUserId())));
    }

    /** 웹 히스토리 정보가져오기*/
    @GetMapping("/web/histories")
    public ResponseEntity<List<HistoryResponse>> webHistories(@RequestParam String category, @RequestParam String userId)
    {
        return ResponseEntity.ok(historyApiMapper.toHistoryReponseList(historyQuery.getHistorys(category,userId)));
    }

    /** 히스토리 삭제 */
    @DeleteMapping("/history/{historyId}")
    public ResponseEntity<Integer> deleteHistory(@PathVariable int historyId) {
        historyUsecase.deleteHistory(historyId);
        return ResponseEntity.ok().build();
    }
}
