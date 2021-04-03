package technomarket.model.dto.requestDTO.userDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Null;

@NoArgsConstructor
@Getter
@Setter
@Component
public class LoginDTO {

    @NotEmpty(message = "Email cannot be null or empty")
    private String email;
    @NotEmpty(message = "Password cannot be null or empty")
    private String password;

}
