package Idea.Archieve.IdeaArchieve.domain.post.entity;

import Idea.Archieve.IdeaArchieve.domain.comment.entity.Comment;
import Idea.Archieve.IdeaArchieve.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
<<<<<<< HEAD:src/main/java/Idea/Archieve/IdeaArchieve/domain/post/entity/Post.java
import java.util.ArrayList;
=======
>>>>>>> f5fd92affad7047fd0a52f67e99faddc67029615:src/main/java/Idea/Archieve/IdeaArchieve/domain/post/Entity/Post.java
import java.util.List;

@Entity
@Getter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", nullable = false)
    private Long postId;

    @Column(name = "title", nullable = false, length = 30)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "category", nullable = false)
    private String category;

    @ColumnDefault("0")
    @Column(name = "heart_count", nullable = false)
    private Integer heartCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

<<<<<<< HEAD:src/main/java/Idea/Archieve/IdeaArchieve/domain/post/entity/Post.java
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post")
    private List<Comment> commentList = new ArrayList<>();
=======
    @OneToMany(mappedBy = "post",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Heart> hearts;
>>>>>>> f5fd92affad7047fd0a52f67e99faddc67029615:src/main/java/Idea/Archieve/IdeaArchieve/domain/post/Entity/Post.java

    public void update(String title, String content, String category) {
        this.title = title;
        this.content = content;
        this.category = category;
    }

    public void updateHeart(Integer heartCount){
        this.heartCount = heartCount;
    }

}
