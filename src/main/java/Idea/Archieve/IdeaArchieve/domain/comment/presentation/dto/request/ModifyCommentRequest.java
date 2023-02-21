package Idea.Archieve.IdeaArchieve.domain.comment.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ModifyCommentRequest {

    @NotBlank(message = "내용은 공백을 허용하지 않습니다.")
    private String content;

}
