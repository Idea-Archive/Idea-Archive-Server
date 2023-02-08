package Idea.Archieve.IdeaArchieve.domain.presentation;

import Idea.Archieve.IdeaArchieve.domain.Entity.Board;
import Idea.Archieve.IdeaArchieve.domain.presentation.dto.request.WriteBoard;
import Idea.Archieve.IdeaArchieve.domain.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping("write")
    public void WriteBoard(WriteBoard writeBoard) {
        boardService.WriteBoard(writeBoard);
    }

    @GetMapping
    public List<Board> ViewBoard() {
        return boardService.ViewBoard();
    }
}
