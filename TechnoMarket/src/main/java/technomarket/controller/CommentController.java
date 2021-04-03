package technomarket.controller;

import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import technomarket.model.dto.requestDTO.CommentDTO;
import technomarket.model.dto.requestDTO.ReactDTO;
import technomarket.model.dto.responseDTO.CommentResponseDTO;
import technomarket.model.dto.responseDTO.MessageDTO;
import technomarket.model.pojo.Comment;
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
    CommentService commentService;
    @Autowired
    ProductService productService;

    @GetMapping("/comments/{id}")
    public List<CommentResponseDTO> getCommentsForProduct(@PathVariable(name = "id") int id ) {
        Product product = productService.getById(id);
        return commentService.getCommentsForProduct(product);
    }

    @PostMapping("/comments/{id}")
    public CommentResponseDTO addComment(@PathVariable(name = "id") int product_id, @Valid @RequestBody CommentDTO commentDTO, HttpSession session) {
        User user = sessionManager.getLoggedUser(session);
        Product product = productService.getById(product_id);
        Comment comment = commentService.addComment(product,user,commentDTO.getComment());
        return  new CommentResponseDTO(comment);
    }

    @PutMapping("/comments/{id}")
    public CommentResponseDTO editComment(@PathVariable(name = "id") int comment_id,@Valid @RequestBody CommentDTO commentDTO,HttpSession session){
        User user = sessionManager.getLoggedUser(session);
        Comment comment = commentService.edit(comment_id,user,commentDTO.getComment());
        return new CommentResponseDTO(comment);
    }

    @DeleteMapping("/comments/{id}")
    public MessageDTO deleteComment(@PathVariable(name = "id") int comment_id, HttpSession session){
        User user = sessionManager.getLoggedUser(session);
        commentService.delete(comment_id,user);
        return new MessageDTO("Comment deleted!");
    }

    @PostMapping("comments/react/{comment_id}")
    public CommentResponseDTO reactComment(HttpSession session, @PathVariable int comment_id, @RequestBody ReactDTO reactDTO){
        Comment comment = commentService.react(reactDTO,sessionManager.getLoggedUser(session), comment_id);
        return new CommentResponseDTO(comment);
    }
}
