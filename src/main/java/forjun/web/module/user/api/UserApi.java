package forjun.web.module.user.api;


import forjun.web.module.user.api.dto.*;
import forjun.web.module.user.application.UserService;
import forjun.web.module.user.domain.User;
import forjun.web.util.JwtUtil;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserApi {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    /** 이용자 회원가입 **/
    @PostMapping("/user/save")
    public ResponseEntity<UserJoinResponse> saveUser(@Valid @RequestBody UserJoinRequest userJoinRequest) {

        User user = User.builder()
                .userId(userJoinRequest.getUserId())
                .userName(userJoinRequest.getUserName())
                .password(userJoinRequest.getPassword())
                .email(userJoinRequest.getEmail())
                .authority("GENERAL")
                .build();

        User result = userService.saveUser(user);
        return ResponseEntity.ok(UserJoinResponse.fromDomain(result));
    }

    /**
     * 이용자 아이디로 중복체크
     **/
    @PostMapping("/user/duplicate")
    public ResponseEntity<Boolean> duplicateUser(@Valid @RequestParam @NotBlank(message = "아이디를 입력하지 않으셨습니다.") String id){
        return ResponseEntity.ok(userService.DuplicateUser(id));
    }

    /** 이용자 아이디로 정보조회 **/
    @PostMapping("/user/view/{userId}")
    public ResponseEntity<UserInfoResponse> viewUser(@PathVariable(name="userId") String userId){
        User user = userService.viewUser(userId);
        return ResponseEntity.ok(UserInfoResponse.fromDomain(user));
    }

    /** 이용자 로그인**/
    @PostMapping("/user/login")
    public ResponseEntity<UserLoginResponse> loginUser(@Valid @RequestBody UserLoginRequest userLoginRequest){

        User user = User.builder()
                .userId(userLoginRequest.getLoginId())
                .password(userLoginRequest.getLoginPassword())
                .build();

        user = userService.Authentication(user);
        return ResponseEntity.ok(UserLoginResponse.fromDomain(user , jwtUtil));
    }
}
