package Idea.Archive.IdeaArchive.domain.auth.repository;

import Idea.Archive.IdeaArchive.domain.auth.entity.RefreshToken;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {

    Optional<RefreshToken> findById(String email);

}
