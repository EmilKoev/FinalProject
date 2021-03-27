package technomarket.model.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import technomarket.model.dto.RegisterRequestUserDTO;

import javax.persistence.*;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String address;
    private String phone;
    private boolean isSubscribed;
    private boolean isAdmin;

    public User(RegisterRequestUserDTO userDTO){
        firstName = userDTO.getFirstName();
        lastName = userDTO.getLastName();
        email = userDTO.getEmail();
        password = userDTO.getPassword();
        isSubscribed = userDTO.isSubscribed();
        phone = userDTO.getPhone();
        address = userDTO.getAddress();
    }
}
