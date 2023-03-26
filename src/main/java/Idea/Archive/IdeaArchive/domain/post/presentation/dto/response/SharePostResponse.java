package Idea.Archive.IdeaArchive.domain.post.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Builder
@Getter
public class SharePostResponse {
    private final String postUrl;
}
