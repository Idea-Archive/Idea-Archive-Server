package Idea.Archive.IdeaArchive.domain.post.entity;

import Idea.Archive.IdeaArchive.domain.member.entity.Member;
import Idea.Archive.IdeaArchive.global.entity.BaseTimeEntity;
import Idea.Archive.IdeaArchive.domain.post.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    @Column(name = "title", nullable = false, length = 30)
    private String title;

    @Column(name = "content", nullable = false, length = 500)
    private String content;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Category> category;

    @ColumnDefault("0")
    @Column(name = "heart_count", nullable = false)
    private Integer heartCount;

    @ColumnDefault("0")
    @Column(name = "comment_count", nullable = false)
    private Integer commentCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ColumnDefault("0")
    @Column(name = "applicant_count", nullable = false)
    private Integer applicantCount;

    @ColumnDefault("0")
    @Column(name = "post_views", nullable = false)
    private Integer views;


    public void update(String title, String content,List<Category> category) {
        this.title = title;
        this.content = content;
        this.category = category;
    }

    public void updateHeart(Integer heartCount) {
        this.heartCount = heartCount;
    }

    public void updateComment(Integer commentCount) {this.commentCount = commentCount;}

    public void updateApplication(Integer applicantCount) {this.applicantCount = applicantCount;}

    public void updateViews(Integer views) {
        this.views = views;
    }

}
