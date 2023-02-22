package Idea.Archieve.IdeaArchieve.domain.post.Entity;

import Idea.Archieve.IdeaArchieve.domain.member.Entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

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

    public void update(String title, String content, String category) {
        this.title = title;
        this.content = content;
        this.category = category;
    }

    public void updateHeart(Integer heartCount){
        this.heartCount = heartCount;
    }

}
