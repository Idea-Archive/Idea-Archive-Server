package Idea.Archieve.IdeaArchieve.domain.post.service;

import Idea.Archieve.IdeaArchieve.domain.post.Entity.Post;
import Idea.Archieve.IdeaArchieve.domain.post.exception.NotExistPostException;
import Idea.Archieve.IdeaArchieve.domain.post.presentation.dto.request.WritePost;
import Idea.Archieve.IdeaArchieve.domain.post.presentation.dto.response.ViewByCategoryResponse;
import Idea.Archieve.IdeaArchieve.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void DeletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    public List<ViewByCategoryResponse> SearchPost(String searchKeyword, String category){
        if(category.isEmpty()){
            List<ViewByCategoryResponse> posts = postRepository.findByTitleContaining(searchKeyword);
            if (posts.size() == 0) {
                throw new NotExistPostException("게시글이 존재하지 않습니다.");
            }
            return posts;
        }else{
            List<ViewByCategoryResponse> posts = postRepository.findByTitleContainingAndCategory(searchKeyword, category);
            if (posts.size() == 0) {
                throw new NotExistPostException("게시글이 존재하지 않습니다.");
            }
            return posts;
        }
    }

    public List<ViewByCategoryResponse> viewPostByCategory(String category){
        List<ViewByCategoryResponse> posts = postRepository.findByCategory(category);
        if (posts.size()==0){
            throw new NotExistPostException("게시글이 존재하지 않습니다.");
        }
        return posts;
    }

}
