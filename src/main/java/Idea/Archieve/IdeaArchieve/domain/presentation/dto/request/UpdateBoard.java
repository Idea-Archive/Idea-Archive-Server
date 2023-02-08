package Idea.Archieve.IdeaArchieve.domain.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBoard {

    private String title;

    private String content;

    private String category;

}