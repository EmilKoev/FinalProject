package technomarket.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.agent.builder.AgentBuilder;
import org.springframework.stereotype.Component;
import technomarket.model.pojo.ProductAttribute;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Component
public class AddProductDTO {

    private String brand;
    private int subCategoryId;
    private double price;
    private String info;
    private int discountId;
    private List<AttributeDTO> attributeList;

}
