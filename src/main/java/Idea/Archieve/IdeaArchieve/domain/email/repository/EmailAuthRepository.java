package Idea.Archieve.IdeaArchieve.domain.email.repository;

import Idea.Archieve.IdeaArchieve.domain.email.entity.EmailAuth;
import org.springframework.data.repository.CrudRepository;

public interface EmailAuthRepository extends CrudRepository<EmailAuth, String> {
}
