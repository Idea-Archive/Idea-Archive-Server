package Idea.Archive.IdeaArchive.domain.post.presentation.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequest {
    private List<String> category;
}
