package technomarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import technomarket.exeptions.AuthenticationException;
import technomarket.exeptions.NotFoundException;
import technomarket.model.dto.CommentReactDTO;
import technomarket.model.dto.CommentResponseDTO;
import technomarket.model.pojo.Comment;
import technomarket.model.pojo.User;
import technomarket.model.repository.CommentsRepository;
import technomarket.model.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentsRepository commentsRepository;
    @Autowired
    UserRepository userRepository;

    public List<CommentResponseDTO> getCommentsByProductId(int id) {
        List<CommentResponseDTO> comments = new ArrayList<>();
        for (Comment c : commentsRepository.getCommentByProductId(id)) {
            comments.add(new CommentResponseDTO(c));
        }
        return comments;
    }

    public CommentResponseDTO addComment(int product_id, int user_id, String comment) {
        Comment c = new Comment();
        c.setComment(comment);
        c.setOwnerId(user_id);
        c.setProductId(product_id);
        c.setPostDate(LocalDateTime.now());
        return new CommentResponseDTO(commentsRepository.save(c));
    }

    public CommentResponseDTO edit(int comment_id, int user_id, String newComment) {
        if (commentsRepository.getById(comment_id) == null){
            throw new NotFoundException("comment not found");
        }
        if (commentsRepository.getById(comment_id).getOwnerId() != user_id) {
            throw new AuthenticationException("you cannot change comments, which are not yours");
        }
        Comment comment = commentsRepository.getById(comment_id);
        comment.setComment(newComment);
        return new CommentResponseDTO(commentsRepository.save(comment));
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

    public void react(CommentReactDTO reactDTO, User user, int comment_id) {
        if (commentsRepository.getById(comment_id) == null){
            throw new NotFoundException("comment not found");
        }
        Comment comment = commentsRepository.getById(comment_id);
        switch (reactDTO.getReact()){
            case -1:
                user.getLikedComments().remove(comment);
                comment.getDislikers().add(user);
                user.getDislikedComments().add(comment);
                comment.getLikers().remove(user);
                break;
            case 1:
                user.getDislikedComments().remove(comment);
                comment.getDislikers().remove(user);
                user.getLikedComments().add(comment);
                comment.getLikers().add(user);
                break;
            default:
                user.getDislikedComments().remove(comment);
                comment.getLikers().remove(user);
                user.getLikedComments().remove(comment);
                comment.getDislikers().remove(user);
                break;
        }
        userRepository.save(user);
    }
}
