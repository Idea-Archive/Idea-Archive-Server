package Idea.Archive.IdeaArchive.domain.comment.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class WriteCommentRequest {

    @NotBlank(message = "내용은 공백을 허용하지 않습니다.")
    private String content;

}
