package Idea.Archive.IdeaArchive.domain.email.repository;

import Idea.Archive.IdeaArchive.domain.email.entity.EmailAuth;
import org.springframework.data.repository.CrudRepository;

public interface EmailAuthRepository extends CrudRepository<EmailAuth, String> {
}
