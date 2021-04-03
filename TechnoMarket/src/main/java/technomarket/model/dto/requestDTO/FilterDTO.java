package technomarket.model.dto.requestDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import technomarket.model.dto.requestDTO.productAndAttributeDTO.AttributeDTO;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Component
public class FilterDTO {

    private String brand;
    private Integer categoryId;
    private Integer subCategoryId;
    private List<AttributeDTO> attributes;
}
