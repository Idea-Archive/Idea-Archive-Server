package Idea.Archieve.IdeaArchieve.domain.email.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RedisHash(value = "emailAuth", timeToLive = 60 * 15)
public class EmailAuth {

    @Id
    private String email;

    @Length(max = 4)
    private String randomValue;

    private Boolean authentication;

    @ColumnDefault("1")
    private Integer attemptCount;
}
