package technomarket.model.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import technomarket.model.dto.RegisterRequestUserDTO;

import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@Setter
@Getter
@Table(name = "users")
public class User {

    @Id //?
    private int id;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private String address;
    private String phone;
    private boolean isSubscribed;
    private boolean isAdmin;

    public User(RegisterRequestUserDTO userDTO){
        name = userDTO.getFirstName();
        lastName = userDTO.getLastName();
        email = userDTO.getEmail();
        password = userDTO.getPassword();
        isAdmin = userDTO.isSubscribed();
        //todo... is admin
    }

}
