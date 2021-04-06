package technomarket.model.dto.requestDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import technomarket.utill.ValidationUtil;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@Component
public class PasswordRequestDTO {

    @NotBlank(message = "Password" + ValidationUtil.NOR_NULL_OR_EMPTY)
    private String password;
}
