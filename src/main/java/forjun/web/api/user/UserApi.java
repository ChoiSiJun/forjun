package forjun.web.api.user;

import forjun.web.api.user.dto.UserInfoResponse;
import forjun.web.api.user.dto.UserJoinRequest;
import forjun.web.api.user.dto.UserJoinResponse;
import forjun.web.application.user.UserService;
import forjun.web.application.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserApi {

    private final UserService userService;

    /** 이용자 회원가입 **/
    @PostMapping(name="/user/save")
    public ResponseEntity<UserJoinResponse> saveUser(UserJoinRequest userJoinRequest) {

        UserDto userDTO = userService.saveUser(userJoinRequest);
        return ResponseEntity.ok(UserJoinResponse.fromService(userDTO));
    }

    /** 이용자 아이디로 정보조회 **/
    @GetMapping(name="/user/view/{userId}")
    public ResponseEntity<UserInfoResponse> viewUser(@PathVariable(name="userId") String userId){
        UserDto userDTO = userService.viewUser(userId);
        return ResponseEntity.ok(UserInfoResponse.fromService(userDTO));
    }
}
