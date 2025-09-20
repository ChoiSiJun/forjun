package forjun.web.module.personal.api;

import forjun.web.config.security.CustomUserDetail;
import forjun.web.module.personal.api.dto.PersonalResponseDto;
import forjun.web.module.personal.api.dto.SavePersonalRequestDto;
import forjun.web.module.personal.api.mapper.PersonalApiMapper;
import forjun.web.module.personal.application.PersonalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/personal")
public class PersonalController {

    private final PersonalApiMapper personalApiMapper;
    private final PersonalService personalService;

    //자기소개서 저장
    @PostMapping
    public ResponseEntity<Void> savePersonal(Authentication auth, @RequestBody SavePersonalRequestDto requestDto) {
        CustomUserDetail user = (CustomUserDetail) auth.getPrincipal();
        personalService.savePersonal(personalApiMapper.toDomain(user.getUserId() , requestDto));
        
        return ResponseEntity.ok().build();
    }

    //자기소개서 가져오기
    @GetMapping("/{id}")
    public ResponseEntity<PersonalResponseDto> getPersonal(Long id) {
        PersonalResponseDto personalResponseDto = personalApiMapper.toPersonalResponseDto(personalService.getPersonal(id));
        return ResponseEntity.ok(personalResponseDto);
    }
}
