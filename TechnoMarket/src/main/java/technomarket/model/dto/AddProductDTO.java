package technomarket.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Component
public class AddProductDTO {

    private String brand;
    @JsonProperty("sub_category_id")
    private int subCategoryId;
    private double price;
    private String info;
    private int discountId;
    @JsonProperty("attribute_list")
    private List<AttributeDTO> attributeList;

}
