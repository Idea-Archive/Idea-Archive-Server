package Idea.Archive.IdeaArchive.domain.auth.presentation.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class OauthWithdrawMemberRequest {

    @NotBlank
    String email;
}
