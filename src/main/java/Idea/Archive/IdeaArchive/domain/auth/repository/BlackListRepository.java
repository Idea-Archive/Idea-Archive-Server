package Idea.Archive.IdeaArchive.domain.auth.repository;

import Idea.Archive.IdeaArchive.domain.auth.entity.BlackList;
import org.springframework.data.repository.CrudRepository;

public interface BlackListRepository extends CrudRepository<BlackList, String> {
}
