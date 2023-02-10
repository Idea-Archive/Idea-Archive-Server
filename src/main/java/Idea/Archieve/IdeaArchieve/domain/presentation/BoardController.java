package Idea.Archieve.IdeaArchieve.domain.presentation;

import Idea.Archieve.IdeaArchieve.domain.Entity.Board;
import Idea.Archieve.IdeaArchieve.domain.presentation.dto.request.UpdateBoard;
import Idea.Archieve.IdeaArchieve.domain.presentation.dto.request.WriteBoard;
import Idea.Archieve.IdeaArchieve.domain.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;

    @PostMapping("write")
    public void WriteBoard(@RequestBody WriteBoard writeBoard) {
        boardService.WriteBoard(writeBoard);
    }

    @GetMapping
    public List<Board> ViewBoard() {
        return boardService.ViewBoard();
    }

    @GetMapping("/{boardId}")
    public Board ViewBoardById(@PathVariable Long boardId) {
        return boardService.ViewBoardById(boardId);
    }

    @PatchMapping("/{boardId}")
    public void UpdateBoard(@PathVariable Long boardId, @RequestBody UpdateBoard updateBoard) {
        boardService.UpdateBoard(boardId, updateBoard);
    }

    @DeleteMapping("/{boardId}")
    public void DeleteBoard(@PathVariable Long boardId) {
        boardService.DeleteBoard(boardId);
    }

    @GetMapping("/search")
    public List<Board> SearchBoard(@RequestParam String searchKeyword,@RequestParam String category){
        if(category.equals("null")){
            log.info("필터 적용 X");
            return boardService.SearchBoard(searchKeyword);
        }else{
            log.info("필터 적용 O");
            return boardService.SearchBoard(searchKeyword,category);
        }
    }

    @GetMapping("/category")
    public List<Board> ViewBoardByCategory(@RequestParam String category){
        return boardService.viewBoardByCategory(category);
    }
}
