package technomarket.model.dto.userDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@Component
public class UserEditRequestDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String oldPassword;
    private String newPassword;
    private String address;
    private String phone;
    private boolean isSubscribed = true;

}
