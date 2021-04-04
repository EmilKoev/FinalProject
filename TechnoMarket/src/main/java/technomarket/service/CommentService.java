package technomarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import technomarket.exeptions.AuthenticationException;
import technomarket.exeptions.BadRequestException;
import technomarket.exeptions.NotFoundException;
import technomarket.model.dto.requestDTO.ReactRequestDTO;
import technomarket.model.dto.responseDTO.CommentResponseDTO;
import technomarket.model.pojo.Comment;
import technomarket.model.pojo.Product;
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

    public List<CommentResponseDTO> getCommentsForProduct(Product product) {
        List<CommentResponseDTO> comments = new ArrayList<>();
        for (Comment c : product.getComments()) {
            comments.add(new CommentResponseDTO(c));
        }
        return comments;
    }

    public Comment addComment(Product product, User user_id, String comment) {
        Comment c = new Comment();
        c.setComment(comment);
        c.setOwnerId(user_id);
        c.setProductId(product);
        c.setPostDate(LocalDateTime.now());
        return commentsRepository.save(c);
    }

    public Comment edit(int comment_id, User user, String newComment) {
        if (commentsRepository.getById(comment_id) == null){
            throw new NotFoundException("comment not found");
        }
        if (commentsRepository.getById(comment_id).getOwnerId() != user) {
            throw new AuthenticationException("you cannot change comments, which are not yours");
        }
        Comment comment = commentsRepository.getById(comment_id);
        comment.setComment(newComment);
        return  commentsRepository.save(comment);
    }

    public void delete(int comment_id, User user) {
        if (commentsRepository.getById(comment_id) == null){
            throw new NotFoundException("comment not found");
        }
        if (commentsRepository.getById(comment_id).getOwnerId() != user) {
            throw new AuthenticationException("you cannot delete comments, which are not yours");
        }
        commentsRepository.delete(commentsRepository.getById(comment_id));
    }


    public Comment react(ReactRequestDTO reactDTO, User user, int comment_id) {
        if (commentsRepository.getById(comment_id) == null){
            throw new NotFoundException("comment not found");
        }
        Comment comment = commentsRepository.getById(comment_id);
        switch (reactDTO.getReact()){
            case -1:
                if (comment.getDislikers().contains(user)){
                    throw new BadRequestException("You already dislike this comment!");
                }
                user.getLikedComments().remove(comment);
                comment.getDislikers().add(user);
                user.getDislikedComments().add(comment);
                comment.getLikers().remove(user);
                break;
            case 1:
                if (comment.getLikers().contains(user)){
                    throw new BadRequestException("You already like this comment!");
                }
                user.getDislikedComments().remove(comment);
                comment.getDislikers().remove(user);
                user.getLikedComments().add(comment);
                comment.getLikers().add(user);
                break;
            case 0:
                user.getDislikedComments().remove(comment);
                comment.getLikers().remove(user);
                user.getLikedComments().remove(comment);
                comment.getDislikers().remove(user);
                break;
            default:
                throw new BadRequestException("Wrong credential!");
        }
        userRepository.save(user);
        return commentsRepository.getById(comment_id);
    }
}
