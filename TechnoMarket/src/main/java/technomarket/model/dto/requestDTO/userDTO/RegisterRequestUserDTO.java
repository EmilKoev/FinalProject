package technomarket.model.dto.requestDTO.userDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Setter
@Getter
@NoArgsConstructor
@Component
public class RegisterRequestUserDTO {

    @NotEmpty(message = "First name cannot be empty or null!")
    private String firstName;
    @NotEmpty(message = "Last name cannot be empty or null!")
    private String lastName;
    @NotEmpty(message = "Email cannot be empty or null!")
    private String email;
    @NotEmpty(message = "Password cannot be empty or null!")
    private String password;
    @NotEmpty(message = "Confirm Password cannot be empty or null!")
    private String confirmPassword;
    @NotEmpty(message = "Phone cannot be empty or null!")
    private String phone;
    @NotEmpty(message = "Address cannot be empty or null!")
    @Size(min = 6, message = "Address must be at least 6 symbols")
    private String address;
    private boolean isSubscribed = true;

}
