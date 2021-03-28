package technomarket.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@NoArgsConstructor
public class CommentReactDTO {
    /*
            1: like
            0: none
           -1: dislike
     */
    private int react;
}
