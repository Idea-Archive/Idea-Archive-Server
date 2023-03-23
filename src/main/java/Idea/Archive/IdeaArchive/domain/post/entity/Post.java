package Idea.Archive.IdeaArchive.domain.post.entity;

import Idea.Archive.IdeaArchive.domain.application.entity.Application;
import Idea.Archive.IdeaArchive.domain.comment.entity.Comment;
import Idea.Archive.IdeaArchive.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
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
    @ElementCollection(fetch=FetchType.EAGER)
    private List<String> category;
    @ColumnDefault("0")
    @Column(name = "heart_count", nullable = false)
    private Integer heartCount;
    @ColumnDefault("0")
    @Column(name = "comment_count",nullable = false)
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
    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post")
    private List<Comment> commentList = new ArrayList<>();
    @OneToMany(mappedBy = "post",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Heart> hearts;
    @OneToMany(mappedBy = "post",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Application> applications;

    @ColumnDefault("0")
    @Column(name = "popular_value")
    private Integer popularValue;


    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
    public void updateHeart(Integer heartCount) {
        this.heartCount = heartCount;
        this.popularValue = this.popularValue + 1;
    }
    public void updatePopularValue() {
        this.popularValue = this.popularValue-1;
    }
    public void updateComment(Integer commentCount) {this.commentCount = commentCount;}
    public void updateApplication(Integer applicantCount) {this.applicantCount = applicantCount;}
    public void updateViews(Integer views) {
        this.views = views;
        this.popularValue = this.popularValue + 1;
    }


}
