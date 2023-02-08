package Idea.Archieve.IdeaArchieve.domain.service;

import Idea.Archieve.IdeaArchieve.domain.presentation.dto.request.WriteBoard;
import Idea.Archieve.IdeaArchieve.domain.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public void WriteBoard(WriteBoard creatBoard) {
        boardRepository.save(creatBoard.toEntity());
    }
}
