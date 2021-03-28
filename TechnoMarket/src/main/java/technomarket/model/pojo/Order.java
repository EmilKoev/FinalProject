package technomarket.model.pojo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    @OneToOne(mappedBy = "order")

    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    private String address;
    private double price;
    @ManyToMany
    @JsonManagedReference
    @JoinTable(
            name = "orders_have_products",
            joinColumns = { @JoinColumn(name = "order_id") },
            inverseJoinColumns = { @JoinColumn(name = "product_id") }
    )
    List<Product> products;

    public Order(User user){
        this.user = user;
        this.address = user.getAddress();
    }

}
