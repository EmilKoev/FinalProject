package technomarket.model.dto.requestDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

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
