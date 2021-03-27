package technomarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import technomarket.exeptions.AuthenticationException;
import technomarket.exeptions.NotFoundException;
import technomarket.model.pojo.Comment;
import technomarket.model.repository.CommentsRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentsRepository commentsRepository;

    public List<Comment> getCommentsByProductId(int id) {
        return commentsRepository.getCommentByProductId(id);
    }

    public Comment addComment(int product_id, int user_id, String comment) {
        Comment c = new Comment();
        c.setComment(comment);
        c.setOwnerId(user_id);
        c.setProductId(product_id);
        c.setPostDate(LocalDateTime.now());
        return commentsRepository.save(c);
    }

    public Comment edit(int comment_id, int user_id, String newComment) {
        if (commentsRepository.getById(comment_id) == null){
            throw new NotFoundException("comment not found");
        }
        if (commentsRepository.getById(comment_id).getOwnerId() != user_id) {
            throw new AuthenticationException("you cannot change comments, which are not yours");
        }
        Comment comment = commentsRepository.getById(comment_id);
        comment.setComment(newComment);
        return commentsRepository.save(comment);
    }

    public void delete(int comment_id, Integer loggedUser) {
        if (commentsRepository.getById(comment_id) == null){
            throw new NotFoundException("comment not found");
        }
        if (commentsRepository.getById(comment_id).getOwnerId() != loggedUser) {
            throw new AuthenticationException("you cannot delete comments, which are not yours");
        }
        commentsRepository.delete(commentsRepository.getById(comment_id));
    }
}
