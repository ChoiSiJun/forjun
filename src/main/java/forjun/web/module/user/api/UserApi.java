package forjun.web.module.user.api;


import forjun.web.module.user.api.dto.*;
import forjun.web.module.user.api.mapper.UserApiMapper;
import forjun.web.module.user.application.port.in.UserQuery;
import forjun.web.module.user.application.port.in.UserUsecase;
import forjun.web.module.user.domain.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserApi {

    private final UserQuery userQuery;
    private final UserUsecase userUsecase;
    private final UserApiMapper userApiMapper;

    /** 이용자 회원가입 **/
    @PostMapping
    public ResponseEntity<Void> saveUser(@Valid @RequestBody UserJoinRequest userJoinRequest) {
        userUsecase.saveUser(userApiMapper.toDomain(userJoinRequest));
        return ResponseEntity.ok().build();
    }

    /**
     * 이용자 아이디로 중복체크
     **/
    @GetMapping("/duplicate")
    public ResponseEntity<Boolean> duplicateUser(@Valid @RequestParam @NotBlank(message = "아이디를 입력하지 않으셨습니다.") String userId){
        return ResponseEntity.ok(userQuery.existsByUserId(userId));
    }

    /** 이용자 아이디로 정보조회 **/
    @GetMapping("/{id}")
    public ResponseEntity<UserInfoResponse> viewUser(@PathVariable(name="id") Long id){
        User user = userQuery.getUser(id);
        return ResponseEntity.ok(UserInfoResponse.fromDomain(user));
    }

    /** 이용자 로그인**/
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@Valid @RequestBody UserLoginRequest request){
        String token = userQuery.authentication(userApiMapper.toDomain(request));
        return ResponseEntity.ok(token);
    }
}
