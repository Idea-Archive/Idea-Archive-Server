package Idea.Archieve.IdeaArchieve.domain.post.service;

import Idea.Archieve.IdeaArchieve.domain.post.Entity.Post;
import Idea.Archieve.IdeaArchieve.domain.post.exception.NotExistPostException;
import Idea.Archieve.IdeaArchieve.domain.post.presentation.dto.request.UpdatePost;
import Idea.Archieve.IdeaArchieve.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void UpdatePost(Long postId, UpdatePost updatePost) {
        Optional<Post> findPost = postRepository.findById(postId);
        findPost.get().update(updatePost.getTitle(), updatePost.getContent(), updatePost.getCategory());
        postRepository.save(findPost.get());
    }

    public void DeletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    /*
        카테고리를 선택하고 검색했을때
     */
    public List<Post> SearchPost(String searchKeyword, String category){
        List<Post> posts = postRepository.findByTitleContainingAndCategory(searchKeyword, category);
        if (posts.size() == 0) {
            throw new NotExistPostException("게시글이 존재하지 않습니다.");
        }
        return posts;
    }
    /*
        카테고리를 선택하지 않고 검색했을때
     */
    public List<Post> SearchPost(String searchKeyword) {
        List<Post> posts = postRepository.findByTitleContaining(searchKeyword);
        if (posts.size() == 0) {
            throw new NotExistPostException("게시글이 존재하지 않습니다.");
        }
        return posts;
    }

    public List<Post> viewPostByCategory(String category){
        List<Post> posts = postRepository.findByCategory(category);
        if (posts.size()==0){
            throw new NotExistPostException("게시글이 존재하지 않습니다.");
        }
        return posts;
    }

}
