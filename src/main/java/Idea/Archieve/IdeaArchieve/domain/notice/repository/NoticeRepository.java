package Idea.Archieve.IdeaArchieve.domain.notice.repository;

import Idea.Archieve.IdeaArchieve.domain.notice.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
