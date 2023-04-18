package Idea.Archive.IdeaArchive.domain.member.presentation.dto.request;


import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class WithdrawMemberRequest {

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
