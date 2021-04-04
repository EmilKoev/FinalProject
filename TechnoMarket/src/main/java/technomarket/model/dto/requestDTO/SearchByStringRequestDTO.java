package technomarket.model.dto.requestDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@Component
public class SearchByStringRequestDTO {

    @NotNull(message = "Search cannot be null")
    private String search;

}
