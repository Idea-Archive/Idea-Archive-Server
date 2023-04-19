package Idea.Archive.IdeaArchive.domain.post.presentation.dto.response;

import Idea.Archive.IdeaArchive.domain.comment.presentation.dto.response.ViewCommentByPostResponse;
import Idea.Archive.IdeaArchive.domain.member.presentation.dto.ViewMemberResponse;
import Idea.Archive.IdeaArchive.domain.post.category.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ViewPostByIdResponse {

    private Long postId;
    private String title;
    private String content;
    private List<Category> category;
    private ViewMemberResponse member;
    private List<ViewCommentByPostResponse> comment;
    private Integer heartCount;
    private Integer commentCount;
    private Integer applicantCount;
    private Integer views;

    @CreatedDate
    private LocalDateTime createdDate;

}
