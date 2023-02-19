package Idea.Archieve.IdeaArchieve.domain.post.presentation.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@Builder
public class ViewByCategoryResponse {

    private final String title;
    private final String content;
    private final String category;
}
