package Idea.Archive.IdeaArchive.domain.member.presentation.dto.response;

import Idea.Archive.IdeaArchive.domain.post.presentation.dto.response.ViewPostResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyPageResponse {

    private String email;
    private String name;
    private String profileImg;

}
