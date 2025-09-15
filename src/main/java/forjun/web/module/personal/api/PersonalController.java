package forjun.web.module.personal.api;

import forjun.web.config.security.CustomUserDetail;
import forjun.web.module.personal.api.dto.SavePersonalRequestDto;
import forjun.web.module.personal.api.mapper.PersonalApiMapper;
import forjun.web.module.personal.application.PersonalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
