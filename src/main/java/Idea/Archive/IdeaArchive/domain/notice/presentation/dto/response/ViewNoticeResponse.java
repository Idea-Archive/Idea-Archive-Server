package Idea.Archive.IdeaArchive.domain.notice.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NotBlank
@Builder
public class ViewNoticeResponse {

    private Long id;
    private String title;
    private String content;
    private String time;

}
