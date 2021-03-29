package technomarket.model.pojo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import technomarket.model.dto.RegisterRequestUserDTO;

import javax.persistence.*;
import java.util.List;

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
    @OneToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;
    private String address;
    private String phone;
    private boolean isSubscribed;
    private boolean isAdmin;
    @OneToMany(mappedBy = "ownerId")
    List<Comment> comments;
    @ManyToMany(mappedBy = "likers")
    private List<Comment> likedComments;
    @ManyToMany(mappedBy = "dislikers")
    List<Comment> dislikedComments;


    public User(RegisterRequestUserDTO userDTO){
        this.firstName = userDTO.getFirstName();
        this.lastName = userDTO.getLastName();
        this.email = userDTO.getEmail();
        this.password = userDTO.getPassword();
        this.isSubscribed = userDTO.isSubscribed();
        this.phone = userDTO.getPhone();
        this.address = userDTO.getAddress();
    }
}
