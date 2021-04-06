package technomarket.model.dto.requestDTO.userDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import technomarket.utill.ValidationUtil;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Null;

@NoArgsConstructor
@Getter
@Setter
@Component
public class LoginRequestDTO {

    @NotBlank(message = "Email" + ValidationUtil.NOR_NULL_OR_EMPTY)
    private String email;
    @NotBlank(message = "Password" + ValidationUtil.NOR_NULL_OR_EMPTY)
    private String password;

}
