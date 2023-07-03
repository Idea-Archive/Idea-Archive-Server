package Idea.Archive.IdeaArchive.domain.member.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class MyPageResponse {

    private String email;
    private String name;
    private String profileImg;

}
