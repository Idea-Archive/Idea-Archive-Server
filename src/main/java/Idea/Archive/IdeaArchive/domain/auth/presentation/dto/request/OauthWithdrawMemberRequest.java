package Idea.Archive.IdeaArchive.domain.auth.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class OauthWithdrawMemberRequest {

    @NotBlank
    String email;
}
