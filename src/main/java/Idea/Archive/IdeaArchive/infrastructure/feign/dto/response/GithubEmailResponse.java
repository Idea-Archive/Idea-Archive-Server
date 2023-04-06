package Idea.Archive.IdeaArchive.infrastructure.feign.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GithubEmailResponse {

    private String email;

}
