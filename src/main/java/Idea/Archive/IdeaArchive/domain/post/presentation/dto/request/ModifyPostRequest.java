package Idea.Archive.IdeaArchive.domain.post.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ModifyPostRequest {

    @NotBlank(message = "제목은 공백을 허용하지 않습니다.")
    @Size(min = 1, max = 30, message = "제목은 1자 ~ 30자 내외입니다.")
    private String title;

    @NotBlank(message = "내용은 공백을 허용하지 않습니다.")
    @Size(min = 1, max = 1000, message = "내용은 1자 ~ 1000자 내외입니다.")
    private String content;

    @NotBlank(message = "카테고리는 공백을 허용하지 않습니다.")
    private String category;

}