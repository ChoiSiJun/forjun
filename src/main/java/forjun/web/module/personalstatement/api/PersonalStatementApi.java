package forjun.web.module.personalstatement.api;

import forjun.web.module.personalstatement.api.dto.PersonalStatementCreateRequest;
import forjun.web.module.personalstatement.api.dto.PersonalStatementInfoResponse;
import forjun.web.module.personalstatement.api.dto.PersonalStatementUpdateRequest;
import forjun.web.module.personalstatement.application.PersonalStatementService;
import forjun.web.module.personalstatement.domain.PersonalStatement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/personalStatement")
public class PersonalStatementApi {

    private final PersonalStatementService personalStatementService;

    @PostMapping
    public ResponseEntity<String> savePersonalStatement(@Valid @RequestBody PersonalStatementCreateRequest personalStatementCreateRequest){

        PersonalStatement personalStatement = PersonalStatement.builder()
                        .type(personalStatementCreateRequest.getType())
                        .label(personalStatementCreateRequest.getLabel())
                        .data(personalStatementCreateRequest.getData())
                        .build();

        personalStatementService.savePersonalStatement(personalStatement);

        return ResponseEntity.ok("자기소개서가 변경되었습니다.");
    }

    @GetMapping
    public ResponseEntity<List<PersonalStatementInfoResponse>> getPersonalStatementList(){

        List<PersonalStatementInfoResponse> personalStatementInfoResponseList = personalStatementService.getPersonalStatementList()
                .stream()
                .map(PersonalStatementInfoResponse::fromDomain)
                .toList();

        return ResponseEntity.ok(personalStatementInfoResponseList);
    }

    @PutMapping
    public ResponseEntity<String> updatePersonalStatement(@Valid @RequestBody PersonalStatementUpdateRequest personalStatementUpdateRequest){

        PersonalStatement personalStatement = PersonalStatement.builder()
                .id(personalStatementUpdateRequest.getId())
                .type(personalStatementUpdateRequest.getType())
                .label(personalStatementUpdateRequest.getLabel())
                .data(personalStatementUpdateRequest.getData())
                .build();

        personalStatementService.savePersonalStatement(personalStatement);

        return ResponseEntity.ok("자기소개서가 변경되었습니다.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePersonalStatement(@PathVariable @Min(1) int id){
        personalStatementService.deletePersonalStatement(id);
        return ResponseEntity.ok("자기소개서가 변경되었습니다.");
    }

}
