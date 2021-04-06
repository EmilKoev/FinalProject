package technomarket.model.dto.requestDTO.productAndAttributeDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import technomarket.utill.ValidationUtil;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Component
@NoArgsConstructor
@Getter
@Setter
public class EditAttributeRequestDTO {

    @NotBlank(message = "Name" + ValidationUtil.NOR_NULL_OR_EMPTY)
    private String name;
    @NotBlank(message = "New name" + ValidationUtil.NOR_NULL_OR_EMPTY)
    @JsonProperty("new_name")
    private String newName;
    @NotBlank(message = "Value" + ValidationUtil.NOR_NULL_OR_EMPTY)
    @JsonProperty("new_value")
    private String newValue;

}
