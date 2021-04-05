package technomarket.model.dto.responseDTO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import technomarket.model.pojo.Discount;
import technomarket.model.pojo.Product;

import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DiscountResponseDTO {

    private int id;
    private String title;
    private int discountPercent;
    private LocalDate startAt;
    private LocalDate endAt;
    private List<ProductResponseDTO> productList;

    public DiscountResponseDTO(Discount discount){
        this.id = discount.getId();
        this.title = discount.getTitle();
        this.discountPercent = discount.getDiscountPercent();
        this.startAt = discount.getStartAt();
        this.endAt = discount.getEndAt();
        productList = new ArrayList<>();
        for (Product p : discount.getProductList()) {
            this.productList.add(new ProductResponseDTO(p));
        }
    }
}
