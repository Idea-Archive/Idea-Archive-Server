package Idea.Archive.IdeaArchive.global.oauth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@ConstructorBinding
@AllArgsConstructor
@ConfigurationProperties(prefix = "auth.github")
public class GithubAuthProperties {

    private final String baseUrl;
    private final String clientId;
    private final String clientSecret;
    private final String redirectUrl;
}
