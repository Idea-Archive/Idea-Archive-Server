package Idea.Archive.IdeaArchive.domain.member.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class ChangeNameRequest {

    @NotBlank
    private String name;
}
