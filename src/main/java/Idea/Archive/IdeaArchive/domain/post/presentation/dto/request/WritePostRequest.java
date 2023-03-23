package Idea.Archive.IdeaArchive.domain.post.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WritePostRequest {

    @NotBlank(message = "제목은 공백을 허용하지 않습니다.")
    private String title;
    @NotBlank(message = "내용은 공백을 허용하지 않습니다.")
    private String content;
    @NotNull
    private List<String> category;


}
