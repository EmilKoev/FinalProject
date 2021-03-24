package technomarket.model.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

}
