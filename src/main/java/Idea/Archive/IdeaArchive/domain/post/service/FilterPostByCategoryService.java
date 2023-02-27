package Idea.Archive.IdeaArchive.domain.post.service;

import Idea.Archive.IdeaArchive.domain.post.exception.NotExistPostException;
import Idea.Archive.IdeaArchive.domain.post.presentation.dto.response.ViewByCategoryResponse;
import Idea.Archive.IdeaArchive.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilterPostByCategoryService {

    private final PostRepository postRepository;

    public List<ViewByCategoryResponse> execute(String category){
        List<ViewByCategoryResponse> posts = postRepository.findByCategory(category);
        if (posts.size()==0){
            throw new NotExistPostException("게시글이 존재하지 않습니다.");
        }
        return posts;
    }
}
