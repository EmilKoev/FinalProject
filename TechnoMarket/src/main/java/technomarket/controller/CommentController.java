package technomarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import technomarket.model.dto.requestDTO.CommentDTO;
import technomarket.model.dto.requestDTO.ReactDTO;
import technomarket.model.dto.responseDTO.CommentResponseDTO;
import technomarket.model.pojo.Product;
import technomarket.model.pojo.User;
import technomarket.service.CommentService;
import technomarket.service.ProductService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RestController
public class CommentController extends Controller{

    @Autowired
    SessionManager sessionManager;
    @Autowired
    CommentService commentService;
    @Autowired
    ProductService productService;

    @GetMapping("/comments/{id}")
    public List<CommentResponseDTO> getCommentsForProduct(@PathVariable(name = "id") int id ) {
        Product product = productService.getById(id);
        return commentService.getCommentsForProduct(product);
    }

    @PostMapping("/comments/{id}")
    public CommentResponseDTO addComment(@PathVariable(name = "id") int product_id, @Valid @RequestBody CommentDTO comment, HttpSession session) {
        User user = sessionManager.getLoggedUser(session);
        Product product = productService.getById(product_id);
        return commentService.addComment(product,user,comment.getComment());
    }

    @PutMapping("/comments/{id}")
    public CommentResponseDTO editComment(@PathVariable(name = "id") int comment_id,@Valid @RequestBody CommentDTO commentDTO,HttpSession session){
        User user = sessionManager.getLoggedUser(session);
        return commentService.edit(comment_id,user,commentDTO.getComment());
    }

    @DeleteMapping("/comments/{id}")
    public void deleteComment(@PathVariable(name = "id") int comment_id,HttpSession session){
        User user = sessionManager.getLoggedUser(session);
        commentService.delete(comment_id,user);
    }

    @PostMapping("comments/react/{comment_id}")
    public void reactComment(HttpSession session, @PathVariable int comment_id, @RequestBody ReactDTO reactDTO){
        commentService.react(reactDTO,sessionManager.getLoggedUser(session), comment_id);
    }
}
