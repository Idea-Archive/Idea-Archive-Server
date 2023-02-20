package Idea.Archieve.IdeaArchieve.domain.member.presentation.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyPageResponse {

    private String email;
    private String name;
    private String password;
}
