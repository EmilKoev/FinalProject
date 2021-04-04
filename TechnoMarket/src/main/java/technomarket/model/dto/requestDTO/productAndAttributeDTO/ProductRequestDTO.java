package technomarket.model.dto.requestDTO.productAndAttributeDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Component
public class ProductRequestDTO {

    @NotNull(message = "Name cannot be null")
    @Size(min = 3, max = 10, message = "Name length cannot be more then 10 and less then 3 characters")
    private String name;
    @NotNull(message = "Brand cannot be null")
    @Size(min = 3, max = 10, message = "Brand length cannot be more then 10 and less then 3 characters")
    private String brand;
    @JsonProperty("sub_category_id")
    @NotNull(message = "sub_category_id cannot be null")
    private int subCategoryId;
    @NotNull(message = "Price cannot be null")
    @Min(value = 0, message = "The price must be a positive number")
    private double price;
    private String info;
    @JsonProperty("discount_id")
    @NotNull(message = "discount_id cannot be null")
    private int discountId;
    @JsonProperty("attribute_list")
    private List<AttributeRequestDTO> attributeList;

}
