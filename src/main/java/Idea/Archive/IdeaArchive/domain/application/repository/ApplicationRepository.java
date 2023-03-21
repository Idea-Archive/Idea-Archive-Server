package Idea.Archive.IdeaArchive.domain.application.repository;

import Idea.Archive.IdeaArchive.domain.application.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application,Long> {
}
