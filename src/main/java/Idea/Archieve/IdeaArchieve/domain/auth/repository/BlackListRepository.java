package Idea.Archieve.IdeaArchieve.domain.auth.repository;

import Idea.Archieve.IdeaArchieve.domain.auth.entity.BlackList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface BlackListRepository extends CrudRepository<BlackList, String> {
}
