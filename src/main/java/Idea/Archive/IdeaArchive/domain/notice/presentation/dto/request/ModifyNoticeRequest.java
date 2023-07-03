package Idea.Archive.IdeaArchive.domain.notice.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class ModifyNoticeRequest {
    @NotBlank
    private String title;
    @NotBlank
    private String content;
}
