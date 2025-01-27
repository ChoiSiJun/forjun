package forjun.web.module.personalstatement.api;

import forjun.web.module.personalstatement.api.dto.PersonalStatementCreateRequest;
import forjun.web.module.personalstatement.api.dto.PersonalStatementInfoResponse;
import forjun.web.module.personalstatement.api.dto.PersonalStatementUpdateRequest;
import forjun.web.module.personalstatement.application.PersonalStatementService;
import forjun.web.module.personalstatement.domain.PersonalStatement;
import jakarta.validation.Valid;
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
                        .title(personalStatementCreateRequest.getTitle())
                        .data(personalStatementCreateRequest.getData())
                        .build();

        personalStatementService.savePersonalStatement(personalStatement);

        return ResponseEntity.ok("자기소개서가 변경되었습니다.");
    }

    @PutMapping
    public ResponseEntity<String> updatePersonalStatement(@Valid @RequestBody PersonalStatementUpdateRequest personalStatementUpdateRequest){

        PersonalStatement personalStatement = PersonalStatement.builder()
                .id(personalStatementUpdateRequest.getId())
                .type(personalStatementUpdateRequest.getType())
                .title(personalStatementUpdateRequest.getTitle())
                .data(personalStatementUpdateRequest.getData())
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
}
