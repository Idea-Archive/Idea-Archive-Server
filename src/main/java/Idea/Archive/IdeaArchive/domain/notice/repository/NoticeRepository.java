package Idea.Archive.IdeaArchive.domain.notice.repository;

import Idea.Archive.IdeaArchive.domain.notice.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
