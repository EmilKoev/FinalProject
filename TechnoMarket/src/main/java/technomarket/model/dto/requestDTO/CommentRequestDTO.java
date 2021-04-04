package technomarket.model.dto.requestDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;

@Setter
@Getter
@Component
@NoArgsConstructor
public class CommentRequestDTO {

    @NotEmpty(message = "Comment cannot be null ot empty")
    private String comment;

}
