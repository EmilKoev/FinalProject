package technomarket.model.dto.requestDTO.userDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@Component
public class UserEditRequestDTO {

    @NotEmpty(message = "First name cannot be empty or null!")
    private String firstName;
    @NotEmpty(message = "Last name cannot be empty or null!")
    private String lastName;
    @Email
    @NotEmpty(message = "Email cannot be empty or null!")
    private String email;
    @NotEmpty(message = "Old Password cannot be empty or null!")
    private String oldPassword;
    @NotEmpty(message = "New Password cannot be empty or null!")
    private String newPassword;
    @NotEmpty(message = "Confirm Password cannot be empty or null!")
    private String confirmNewPassword;
    @NotEmpty(message = "Address cannot be empty or null!")
    @Size(min = 6, message = "Address must be at least 6 symbols")
    private String address;
    private String phone;
    private boolean isSubscribed;

}
