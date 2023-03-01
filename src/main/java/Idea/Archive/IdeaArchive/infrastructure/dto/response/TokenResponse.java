package Idea.Archive.IdeaArchive.infrastructure.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter @Builder
@AllArgsConstructor
public class TokenResponse {

    private String accessToken;

    private final String refreshToken;

    private final LocalDateTime expiredAt;

}
