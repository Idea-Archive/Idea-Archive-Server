package Idea.Archive.IdeaArchive.domain.notice.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class ViewNoticeByIdResponse {

    private Long id;
    private String title;
    private String content;

    @CreatedDate
    private LocalDateTime createdDate;
}