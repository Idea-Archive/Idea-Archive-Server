package Idea.Archive.IdeaArchive.domain.member.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class MakeNewPasswordRequest {

    @NotBlank
    private String password;
    @NotBlank
    private String checkPassword;
}
