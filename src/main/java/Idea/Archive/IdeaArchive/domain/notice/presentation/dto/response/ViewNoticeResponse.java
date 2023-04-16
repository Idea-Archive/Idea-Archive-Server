package Idea.Archive.IdeaArchive.domain.notice.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NotBlank
@Builder
public class ViewNoticeResponse {

    private Long id;
    private String title;
    private String content;

    @CreatedDate
    private LocalDateTime createdDate;

}
