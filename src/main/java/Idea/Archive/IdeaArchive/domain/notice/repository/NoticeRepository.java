package Idea.Archive.IdeaArchive.domain.notice.repository;

import Idea.Archive.IdeaArchive.domain.notice.entity.Notice;
import org.springframework.data.repository.CrudRepository;

public interface NoticeRepository extends CrudRepository<Notice, Long> {
}
