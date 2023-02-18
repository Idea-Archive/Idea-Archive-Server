package Idea.Archieve.IdeaArchieve.domain.auth.repository;

import Idea.Archieve.IdeaArchieve.domain.auth.entity.BlackList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlackListRepository extends JpaRepository<BlackList, String> {
}
