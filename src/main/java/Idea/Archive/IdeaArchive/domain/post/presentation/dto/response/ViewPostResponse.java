package Idea.Archive.IdeaArchive.domain.post.presentation.dto.response;

import Idea.Archive.IdeaArchive.domain.member.presentation.dto.ViewMemberResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ViewPostResponse {

    private Long id;
    private String title;
    private String content;
    private List<String> category;
    private Integer heartCount;
    private Integer commentCount;
    private ViewMemberResponse member;
    private String time;

}
