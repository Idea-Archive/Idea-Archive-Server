package Idea.Archieve.IdeaArchieve.domain.post.service;

import Idea.Archieve.IdeaArchieve.domain.post.Entity.Post;
import Idea.Archieve.IdeaArchieve.domain.post.exception.NotExistPostException;
import Idea.Archieve.IdeaArchieve.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilterPostService {

    private final PostRepository postRepository;


    /*
    카테고리를 선택하고 검색했을때
    */
    public List<Post> execute(String searchKeyword, String category){
        List<Post> posts = postRepository.findByTitleContainingAndCategory(searchKeyword, category);
        if (posts.size() == 0) {
            throw new NotExistPostException("게시글이 존재하지 않습니다.");
        }
        return posts;
    }

    /*
        카테고리를 선택하지 않고 검색했을때
    */
    public List<Post> execute(String searchKeyword) {
        List<Post> posts = postRepository.findByTitleContaining(searchKeyword);
        if (posts.size() == 0) {
            throw new NotExistPostException("게시글이 존재하지 않습니다.");
        }
        return posts;
    }
}
