package Idea.Archieve.IdeaArchieve.domain.repository;

import Idea.Archieve.IdeaArchieve.domain.Entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
