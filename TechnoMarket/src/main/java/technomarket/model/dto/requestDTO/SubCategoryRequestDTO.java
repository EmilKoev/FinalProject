package technomarket.model.dto.requestDTO;

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

@Setter
@Getter
@NoArgsConstructor
@Component
public class SubCategoryRequestDTO {

    @JsonProperty("category_id")
    private int categoryId;
    @NotBlank(message = "Name" + ValidationUtil.NOR_NULL_OR_EMPTY)
    private String name;

}
