package technomarket.model.dto.userDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Getter
@Setter
@Component
public class LoginDTO {

    private String email;
    private String password;

}
