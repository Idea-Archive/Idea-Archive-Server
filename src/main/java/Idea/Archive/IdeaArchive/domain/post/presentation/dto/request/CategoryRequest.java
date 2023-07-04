package Idea.Archive.IdeaArchive.domain.post.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class CategoryRequest {
    private List<String> category;
}
