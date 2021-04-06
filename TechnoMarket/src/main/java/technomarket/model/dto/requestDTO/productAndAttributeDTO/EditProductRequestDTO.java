package technomarket.model.dto.requestDTO.productAndAttributeDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import technomarket.utill.ValidationUtil;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Component
@NoArgsConstructor
@Getter
@Setter
public class EditProductRequestDTO {

    @NotBlank(message = "Name" + ValidationUtil.NOR_NULL_OR_EMPTY)
    @Size(min = ValidationUtil.MIN_NAME_LENGTH, max = ValidationUtil.MAX_NAME_LENGTH,
            message = ValidationUtil.LENGTH_LIMITS_FOR_NAME)
    private String name;
    @NotNull(message = "Brand" + ValidationUtil.NOR_NULL_OR_EMPTY)
    @Size(min = ValidationUtil.MIN_BRAND_LENGTH, max = ValidationUtil.MAX_BRAND_LENGTH,
            message = ValidationUtil.LENGTH_LIMITS_FOR_BAND)
    private String brand;
    @JsonProperty("sub_category_id")
    @NotNull(message = "sub_category_id" + ValidationUtil.NOT_NULL)
    private int subCategoryId;
    @NotNull(message = "Price" + ValidationUtil.NOT_NULL)
    @Min(value = 0, message = "The price" + ValidationUtil.POSITIVE_NUMBER)
    private double price;
    private String info;
    @JsonProperty("discount_id")
    @NotNull(message = "discount_id" + ValidationUtil.NOT_NULL)
    private int discountId;

}
