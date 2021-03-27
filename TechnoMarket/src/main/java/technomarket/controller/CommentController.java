package technomarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import technomarket.model.dto.CommentDTO;
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
    public List<Comment> getCommentsForProduct(@PathVariable(name = "id") int id ) {
        return commentService.getCommentsByProductId(id);
    }

    @PostMapping("/comments/{id}")
    public Comment addComment(@PathVariable(name = "id") int product_id, @RequestBody CommentDTO comment, HttpSession session) {
        User user = sessionManager.getLoggedUser(session);
        return commentService.addComment(product_id,user.getId(),comment.getComment());
    }

    @PutMapping("/comments/{id}")
    public Comment editComment(@PathVariable(name = "id") int comment_id,@RequestBody CommentDTO commentDTO,HttpSession session){
        return commentService.edit(comment_id,sessionManager.getLoggedUser(session).getId(),commentDTO.getComment());
    }

    @DeleteMapping("/comments/{id}")
    public void deleteComment(@PathVariable(name = "id") int comment_id,HttpSession session){
        commentService.delete(comment_id,sessionManager.getLoggedUser(session).getId());
    }
}
