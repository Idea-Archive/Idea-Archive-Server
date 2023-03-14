package Idea.Archive.IdeaArchive.infrastructure.feign.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@Getter
@NoArgsConstructor
public class TokenResponse {

    private String access_token;
    private String express_in;
    private String scope;
    private String token_type;
    private String id_token;
}
