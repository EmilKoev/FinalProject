package technomarket.model.dto.userDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Setter
@Getter
@NoArgsConstructor
@Component
public class RegisterRequestUserDTO {

    private String firstName;
    private String lastName;
    private String password;
    private String confirmPassword;
    private String email;
    private String phone;
    private String address;
    private boolean isSubscribed = true;

}
