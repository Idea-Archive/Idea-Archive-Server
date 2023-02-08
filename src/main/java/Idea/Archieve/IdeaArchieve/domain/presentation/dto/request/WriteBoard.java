package Idea.Archieve.IdeaArchieve.domain.presentation.dto.request;

import Idea.Archieve.IdeaArchieve.domain.Entity.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WriteBoard {

    private String title;

    private String content;

    private String category;

    public Board toEntity() {
        return Board.builder()
                .title(title)
                .content(content)
                .category(category)
                .build();
    }
}
