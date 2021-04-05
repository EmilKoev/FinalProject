package technomarket.model.pojo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne()
    @JoinColumn(name = "user_id")
    @JsonBackReference
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
    private LocalDateTime finishedAt;
}
