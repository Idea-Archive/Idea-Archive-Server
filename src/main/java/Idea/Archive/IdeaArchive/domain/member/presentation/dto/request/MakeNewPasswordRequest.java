package Idea.Archieve.IdeaArchieve.domain.member.presentation.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class MakeNewPasswordRequest {

    @NotBlank
    private String password;
    @NotBlank
    private String checkPassword;
}
