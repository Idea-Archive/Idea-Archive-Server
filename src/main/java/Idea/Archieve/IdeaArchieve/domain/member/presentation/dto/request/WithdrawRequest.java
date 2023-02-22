package Idea.Archieve.IdeaArchieve.domain.member.presentation.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class WithdrawRequest {

    @NotBlank
    private String password;
}
