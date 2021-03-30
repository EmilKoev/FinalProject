package technomarket.model.dto.responseDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import technomarket.model.pojo.Comment;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Component
public class CommentResponseDTO {

    private int id;
    private int productId;
    private int ownerId;
    private String comment;
    private LocalDateTime postDate;
    private int likes;
    private int dislikes;

    public CommentResponseDTO(Comment comment){
        this.id = comment.getId();
        this.productId = comment.getProductId().getId();
        this.ownerId = comment.getOwnerId().getId();
        this.comment = comment.getComment();
        this.postDate = comment.getPostDate();
        this.likes = comment.getLikers().size();
        this.dislikes = comment.getDislikers().size();
    }
}
