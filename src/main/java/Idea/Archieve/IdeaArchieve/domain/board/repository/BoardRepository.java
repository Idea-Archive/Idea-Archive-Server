package Idea.Archieve.IdeaArchieve.domain.board.repository;

import Idea.Archieve.IdeaArchieve.domain.board.Entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByTitleContainingAndCategory(String searchKeyword, String category);
    List<Board> findByTitleContaining(String searchKeyword);
    List<Board> findByCategory(String category);
}
