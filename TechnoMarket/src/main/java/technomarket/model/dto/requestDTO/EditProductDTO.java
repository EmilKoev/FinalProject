package technomarket.model.dto.requestDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@Getter
@Setter
public class EditProductDTO {

    private String name;
    private String brand;
    @JsonProperty("sub_category_id")
    private int subCategoryId;
    private double price;
    private String info;
    @JsonProperty("discount_id")
    private int discountId;

}
