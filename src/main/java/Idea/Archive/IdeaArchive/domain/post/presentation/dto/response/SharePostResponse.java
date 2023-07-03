package Idea.Archive.IdeaArchive.domain.post.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class SharePostResponse {
    private final String postUrl;
}
