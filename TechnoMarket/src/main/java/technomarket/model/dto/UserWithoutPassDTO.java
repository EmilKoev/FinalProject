package technomarket.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import technomarket.model.pojo.User;

@NoArgsConstructor
@Getter
@Setter
@Component
public class UserWithoutPassDTO {

    private int id;
    private String name;
    private String lastName;
    private String email;
    private String address;
    private String phone;
    private boolean isSubscribed;
    private boolean isAdmin;

    public UserWithoutPassDTO(User user){
        id = user.getId();
        name = user.getFirstName();
        lastName = user.getLastName();
        email = user.getEmail();
        address = user.getAddress();
        phone = user.getPhone();
        isSubscribed = user.isSubscribed();
        isAdmin = user.isAdmin();
    }

}
