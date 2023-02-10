package Idea.Archieve.IdeaArchieve.domain.service;

import Idea.Archieve.IdeaArchieve.domain.Entity.Board;
import Idea.Archieve.IdeaArchieve.domain.exception.NotExistBoardException;
import Idea.Archieve.IdeaArchieve.domain.presentation.dto.request.UpdateBoard;
import Idea.Archieve.IdeaArchieve.domain.presentation.dto.request.WriteBoard;
import Idea.Archieve.IdeaArchieve.domain.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public void WriteBoard(WriteBoard creatBoard) {
        boardRepository.save(creatBoard.toEntity());
    }

    public List<Board> ViewBoard() {
        return boardRepository.findAll();
    }

    public Board ViewBoardById(Long boardId) {
        return boardRepository.findById(boardId)
                .orElseThrow(() -> new NotExistBoardException("존재하지 않는 게시판입니다."));
    }

    public void UpdateBoard(Long boardId, UpdateBoard updateBoard) {
        Optional<Board> findBoard = boardRepository.findById(boardId);
        findBoard.get().update(updateBoard.getTitle(), updateBoard.getContent(), updateBoard.getCategory());
        boardRepository.save(findBoard.get());
    }

    public void DeleteBoard(Long boardId) {
        boardRepository.deleteById(boardId);
    }

    /*
        카테고리를 선택하고 검색했을때
     */
    public List<Board> SearchBoard(String searchKeyword, String category){
        List<Board> boards = boardRepository.findByTitleContainingAndCategory(searchKeyword, category);
        if (boards.size() == 0) {
            throw new NotExistBoardException("게시글이 존재하지 않습니다.");
        }
        return boards;
    }
    /*
        카테고리를 선택하지 않고 검색했을때
     */
    public List<Board> SearchBoard(String searchKeyword) {
        List<Board> boards = boardRepository.findByTitleContaining(searchKeyword);
        if (boards.size() == 0) {
            throw new NotExistBoardException("게시글이 존재하지 않습니다.");
        }
        return boards;
    }

    public List<Board> viewBoardByCategory(String category){
        List<Board> boards = boardRepository.findByCategory(category);
        if (boards.size()==0){
            throw new NotExistBoardException("게시글이 존재하지 않습니다.");
        }
        return boards;
    }

}
