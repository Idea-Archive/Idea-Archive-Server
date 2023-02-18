package Idea.Archieve.IdeaArchieve.domain.auth.repository;

import Idea.Archieve.IdeaArchieve.domain.auth.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {

    Optional<RefreshToken> findById(String email);

}
