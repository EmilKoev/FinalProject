package technomarket.model.dto.requestDTO.productAndAttributeDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import technomarket.utill.ValidationUtil;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class AttributeRequestDTO {

    @NotBlank(message = "Name" + ValidationUtil.NOR_NULL_OR_EMPTY)
    private String name;
    @NotBlank(message = "Value" + ValidationUtil.NOR_NULL_OR_EMPTY)
    private String value;

}
