package technomarket.model.pojo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference
    private Product productId;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User ownerId;
    private String comment;
    private LocalDateTime postDate;
    @ManyToMany
    @JoinTable(
            name ="liked_comments",
            joinColumns = {@JoinColumn(name = "comment_id")},
            inverseJoinColumns ={@JoinColumn(name = "user_id")}
    )
    List<User> likers;
    @ManyToMany
    @JoinTable(
            name ="disliked_comments",
            joinColumns = {@JoinColumn(name = "comment_id")},
            inverseJoinColumns ={@JoinColumn(name = "user_id")}
    )
    List<User> dislikers;

    public Comment(){
        likers = new ArrayList<>();
        dislikers = new ArrayList<>();
    }
}
