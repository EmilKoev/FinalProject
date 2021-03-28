package technomarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import technomarket.model.dto.CommentDTO;
import technomarket.model.dto.CommentReactDTO;
import technomarket.model.dto.CommentResponseDTO;
import technomarket.model.pojo.Comment;
import technomarket.model.pojo.User;
import technomarket.service.CommentService;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class CommentController extends Controller{

    @Autowired
    SessionManager sessionManager;
    @Autowired
    CommentService commentService;

    @GetMapping("/comments/{id}")
    public List<CommentResponseDTO> getCommentsForProduct(@PathVariable(name = "id") int id ) {
        return commentService.getCommentsByProductId(id);
    }

    @PostMapping("/comments/{id}")
    public CommentResponseDTO addComment(@PathVariable(name = "id") int product_id, @RequestBody CommentDTO comment, HttpSession session) {
        User user = sessionManager.getLoggedUser(session);
        return commentService.addComment(product_id,user.getId(),comment.getComment());
    }

    @PutMapping("/comments/{id}")
    public CommentResponseDTO editComment(@PathVariable(name = "id") int comment_id,@RequestBody CommentDTO commentDTO,HttpSession session){
        return commentService.edit(comment_id,sessionManager.getLoggedUser(session).getId(),commentDTO.getComment());
    }

    @DeleteMapping("/comments/{id}")
    public void deleteComment(@PathVariable(name = "id") int comment_id,HttpSession session){
        commentService.delete(comment_id,sessionManager.getLoggedUser(session).getId());
    }

    @PostMapping("comments/react/{comment_id}")
    public void reactComment(HttpSession session, @PathVariable int comment_id, @RequestBody CommentReactDTO reactDTO){
        commentService.react(reactDTO,sessionManager.getLoggedUser(session), comment_id);
    }
}
