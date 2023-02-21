package Idea.Archieve.IdeaArchieve.domain.chat.repository;

import Idea.Archieve.IdeaArchieve.domain.chat.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {
}
