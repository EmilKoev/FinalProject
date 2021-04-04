package technomarket.model.dto.requestDTO.productAndAttributeDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Component
@NoArgsConstructor
@Getter
@Setter
public class EditAttributeRequestDTO {

    @NotEmpty(message = "Name cannot be null ot empty!")
    private String name;
    @NotEmpty(message = "New name cannot be null ot empty!")
    @JsonProperty("new_name")
    private String newName;
    @NotEmpty(message = "Value cannot be null ot empty!")
    @JsonProperty("new_value")
    private String newValue;

}
