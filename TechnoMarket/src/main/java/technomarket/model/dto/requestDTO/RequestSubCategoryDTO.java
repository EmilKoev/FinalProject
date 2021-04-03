package technomarket.model.dto.requestDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
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
@Component
public class RequestSubCategoryDTO {

    @JsonProperty("category_id")
    private int categoryId;
    @NotEmpty(message = "Name cannot be null ot empty!")
    private String name;

}
