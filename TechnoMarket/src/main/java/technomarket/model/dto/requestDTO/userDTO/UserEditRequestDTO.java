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

@Getter
@Setter
@NoArgsConstructor
@Component
public class UserEditRequestDTO {

    @NotBlank(message = "First name" + ValidationUtil.NOR_NULL_OR_EMPTY)
    private String firstName;
    @NotBlank(message = "Last name" + ValidationUtil.NOR_NULL_OR_EMPTY)
    private String lastName;
    @Email
    @NotBlank(message = "Email" + ValidationUtil.NOR_NULL_OR_EMPTY)
    private String email;
    @NotBlank(message = "Old Password" + ValidationUtil.NOR_NULL_OR_EMPTY)
    private String oldPassword;
    @NotBlank(message = "New Password" + ValidationUtil.NOR_NULL_OR_EMPTY)
    private String newPassword;
    @NotBlank(message = "Confirm Password" + ValidationUtil.NOR_NULL_OR_EMPTY)
    private String confirmNewPassword;
    @NotBlank(message = "Address" + ValidationUtil.NOR_NULL_OR_EMPTY)
    @Size(min = 6, message = ValidationUtil.LENGTH_LIMITS_FOR_ADDRESS)
    private String address;
    private String phone;
    private boolean isSubscribed;

}
