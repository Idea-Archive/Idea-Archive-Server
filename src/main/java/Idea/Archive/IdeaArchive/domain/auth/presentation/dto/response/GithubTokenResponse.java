package Idea.Archive.IdeaArchive.domain.auth.presentation.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GithubTokenResponse {

    private String access_token;
    private String token_type;
    private String scope;

}
