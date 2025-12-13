package forjun.web.module.personal.api;

import forjun.web.config.security.CustomUserDetail;
import forjun.web.module.personal.api.dto.PersonalResponseDto;
import forjun.web.module.personal.api.dto.SavePersonalRequestDto;
import forjun.web.module.personal.api.mapper.PersonalApiMapper;
import forjun.web.module.personal.application.PersonalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/** 자기소개서 제어 API*/
@Tag(name = "자기소개서 제어 API", description = "자기소개서 제어 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/personal")
public class PersonalController {

    // Mapper
    private final PersonalApiMapper personalApiMapper;

    // Service
    private final PersonalService personalService;

    //자기소개서 저장
    @Operation(summary = "자기소개서 저장", description = "자기소개서 저장")
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    public ResponseEntity<Void> savePersonal(@AuthenticationPrincipal CustomUserDetail user, @Valid @RequestBody SavePersonalRequestDto requestDto) {

        // 인증 정보 가져오기

        personalService.savePersonal(personalApiMapper.toDomain(user.getUserId() , requestDto));
        return ResponseEntity.ok().build();
    }

    //자기소개서 가져오기
    @GetMapping
    public ResponseEntity<PersonalResponseDto> getPersonal(Authentication auth) {
        CustomUserDetail user = (CustomUserDetail) auth.getPrincipal();
        PersonalResponseDto personalResponseDto = personalApiMapper.toPersonalResponseDto(personalService.getPersonal(user.getUserId()));
        return ResponseEntity.ok(personalResponseDto);
    }
}
