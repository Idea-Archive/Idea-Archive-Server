package Idea.Archieve.IdeaArchieve.domain.member.presentation.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class ChangePasswordRequest {

    @NotNull
    private String currentPassword;
    @NotNull
    private String newPassword;
}
