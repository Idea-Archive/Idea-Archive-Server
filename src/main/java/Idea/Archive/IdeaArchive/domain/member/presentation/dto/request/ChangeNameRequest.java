package Idea.Archive.IdeaArchieve.domain.member.presentation.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class ChangeNameRequest {
    @NotBlank
    private String name;
}
