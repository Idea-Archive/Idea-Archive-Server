package Idea.Archive.IdeaArchive.domain.auth.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;


@Getter @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
@RedisHash(value = "refreshToken")
public class RefreshToken {

    @Id
    private String email;
    @Indexed
    private String refreshToken;

    @TimeToLive
    private long expiredAt;

    public void exchangeRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

}
