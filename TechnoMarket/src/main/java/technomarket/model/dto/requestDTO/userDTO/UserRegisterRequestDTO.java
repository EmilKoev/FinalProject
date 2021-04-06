package technomarket.model.dto.requestDTO.userDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import technomarket.utill.ValidationUtil;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Setter
@Getter
@NoArgsConstructor
@Component
public class UserRegisterRequestDTO {

    @NotBlank(message = "First name" + ValidationUtil.NOR_NULL_OR_EMPTY)
    private String firstName;
    @NotBlank(message = "Last name" + ValidationUtil.NOR_NULL_OR_EMPTY)
    private String lastName;
    @NotBlank(message = "Email" + ValidationUtil.NOR_NULL_OR_EMPTY)
    @Email(message = "Not valid email!")
    private String email;
    @NotBlank(message = "Password" + ValidationUtil.NOR_NULL_OR_EMPTY)
    private String password;
    @NotBlank(message = "Confirm Password" + ValidationUtil.NOR_NULL_OR_EMPTY)
    private String confirmPassword;
    private String phone;
    @NotBlank(message = "Address" + ValidationUtil.NOR_NULL_OR_EMPTY)
    @Size(min = ValidationUtil.MIN_ADDRESS_LENGTH, message = ValidationUtil.LENGTH_LIMITS_FOR_ADDRESS)
    private String address;
    private boolean isSubscribed = true;

}
