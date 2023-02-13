package Idea.Archieve.IdeaArchieve.domain.auth.repository;

import Idea.Archieve.IdeaArchieve.domain.auth.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {
}
