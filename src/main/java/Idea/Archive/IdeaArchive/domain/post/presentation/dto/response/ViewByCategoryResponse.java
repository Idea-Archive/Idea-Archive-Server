package Idea.Archive.IdeaArchive.domain.post.presentation.dto.response;


import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ViewByCategoryResponse {

    private String title;
    private String content;
    private String category;
}
