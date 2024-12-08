package forjun.web.api.user;

import forjun.web.api.user.dto.*;
import forjun.web.application.user.UserService;
import forjun.web.application.user.dto.UserDto;
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

        UserDto userDTO = UserDto.builder()
                .userId(userJoinRequest.getUserId())
                .userName(userJoinRequest.getUserName())
                .password(userJoinRequest.getPassword())
                .email(userJoinRequest.getEmail())
                .authority("GENERAL")
                .build();

        UserDto result = userService.saveUser(userDTO);
        return ResponseEntity.ok(UserJoinResponse.fromDto(result));
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
        UserDto userDTO = userService.viewUser(userId);
        return ResponseEntity.ok(UserInfoResponse.fromDto(userDTO));
    }

    /** 이용자 로그인**/
    @PostMapping("/user/login")
    public ResponseEntity<UserLoginResponse> loginUser(@Valid @RequestBody UserLoginRequest userLoginRequest){

        UserDto userDto = UserDto.builder()
                .userId(userLoginRequest.getLoginId())
                .password(userLoginRequest.getLoginPassword())
                .build();

        UserDto userDTO = userService.Authentication(userDto);
        return ResponseEntity.ok(UserLoginResponse.fromDto(userDTO , jwtUtil));
    }
}
