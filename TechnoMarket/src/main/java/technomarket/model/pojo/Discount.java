package technomarket.model.pojo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.catalina.LifecycleState;
import technomarket.model.dto.requestDTO.DiscountDTO;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "discounts")
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    @JsonProperty("discount_percent")
    private int discountPercent;
    @JsonProperty("start_at")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate startAt;
    @JsonProperty("end_at")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate endAt;
    @OneToMany(mappedBy = "discount")
    @JsonBackReference
    private List<Product> productList;

    public Discount(DiscountDTO discountDTO){
        this.title = discountDTO.getTitle();
        this.discountPercent = discountDTO.getDiscountPercent();
        this.startAt = discountDTO.getStartAt();
        this.endAt = discountDTO.getEndAt();
        this.productList = new ArrayList<>();
    }

}
