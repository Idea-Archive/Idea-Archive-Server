package Idea.Archive.IdeaArchive.domain.auth.entity;

import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import org.springframework.data.annotation.Id;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash(value = "blackList")
public class BlackList {

    @Id
    private String accessToken;

    private String email;
}
