package Idea.Archive.IdeaArchive.domain.notice.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class WriteNoticeRequest {

    @NotBlank(message = "제목은 공백을 허용하지 않습니다.")
    private String title;

    @NotBlank(message = "제목은 공백을 허용하지 않습니다.")
    private String content;
}
