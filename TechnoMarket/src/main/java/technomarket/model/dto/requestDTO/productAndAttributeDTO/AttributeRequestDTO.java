package technomarket.model.dto.requestDTO.productAndAttributeDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class AttributeRequestDTO {

    @NotEmpty(message = "Name cannot be null ot empty!")
    private String name;
    @NotEmpty(message = "Value cannot be null ot empty!")
    private String value;

}
