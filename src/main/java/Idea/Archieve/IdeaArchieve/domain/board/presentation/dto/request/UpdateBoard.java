package Idea.Archieve.IdeaArchieve.domain.board.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBoard {

    @NotBlank(message = "제목은 공백을 허용하지 않습니다.")
    private String title;

    @NotBlank(message = "내용은 공백을 허용하지 않습니다.")
    private String content;

    @NotBlank(message = "카테고리는 공백을 허용하지 않습니다.")
    private String category;

}