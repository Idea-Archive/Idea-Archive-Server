package Idea.Archive.IdeaArchive.domain.notice.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ViewNoticeByIdResponse {

    private Long id;
    private String title;
    private String content;

}
