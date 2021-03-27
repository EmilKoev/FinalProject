package technomarket.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import technomarket.model.pojo.Comment;

import java.util.List;

@Repository
public interface CommentsRepository extends JpaRepository<Comment,Integer> {

    List<Comment> getCommentByProductId(Integer productId);
    Comment getById(Integer id);
}
