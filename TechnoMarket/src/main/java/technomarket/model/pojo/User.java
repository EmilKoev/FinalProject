package technomarket.model.pojo;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import technomarket.model.dto.requestDTO.userDTO.UserRegisterRequestDTO;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@Setter
@Getter
@Entity
@EqualsAndHashCode
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
    private List<Comment> dislikedComments;
    @ManyToMany(mappedBy = "likers")
    private List<Product> likedProducts;
    @ManyToMany(mappedBy = "dislikers")
    private List<Product> dislikedProducts;


    public User(UserRegisterRequestDTO userDTO){
        this.firstName = userDTO.getFirstName();
        this.lastName = userDTO.getLastName();
        this.email = userDTO.getEmail();
        this.password = userDTO.getPassword();
        this.isSubscribed = userDTO.isSubscribed();
        this.phone = userDTO.getPhone();
        this.address = userDTO.getAddress();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return this.id == user.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
