package forjun.web.domain.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users")
public class UserEntity {

    @Id
    private String userId;

    private String password;

    private String userName;

    private String email;

    //유저정보 변경
    @Builder(builderMethodName = "updateUser")
    public void updateUser(String userName , String email){

        if(userName != null && !userName.isEmpty()){
            this.userName = userName;
        }

        if(email != null && !email.isEmpty()){
            this.email = email;
        }
    }

    //패스워드 변경
    @Builder(builderMethodName = "updatePassword")
    public void updatePassword(String password){
        if(password != null && !password.isEmpty()){
            this.password = password;
        }
    }
}
