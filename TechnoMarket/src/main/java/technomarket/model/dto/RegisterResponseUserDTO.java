package technomarket.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import technomarket.model.pojo.User;

@Setter
@Getter
@NoArgsConstructor
@Component
public class RegisterResponseUserDTO {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private boolean isSubscribed = true;

    public RegisterResponseUserDTO(User user){
        id = user.getId();
        firstName = user.getName();
        lastName = user.getLastName();
        email = user.getEmail();
        isSubscribed = user.isSubscribed();
    }

}
