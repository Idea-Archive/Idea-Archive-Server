package Idea.Archieve.IdeaArchieve.domain.post.presentation;

import Idea.Archieve.IdeaArchieve.domain.post.Entity.Post;
import Idea.Archieve.IdeaArchieve.domain.post.presentation.dto.request.UpdatePost;
import Idea.Archieve.IdeaArchieve.domain.post.presentation.dto.request.WritePost;
import Idea.Archieve.IdeaArchieve.domain.post.presentation.dto.response.ViewByCategoryResponse;
import Idea.Archieve.IdeaArchieve.domain.post.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final WritePostService writePostService;
    private final PostService postService;
    private final ViewPostService viewPostService;
    private final ViewPostByIdService viewPostByIdService;
    private final UpdatePostService updatePostService;

    @PostMapping("write")
    public void WritePost(@RequestBody WritePost writePost) {
        writePostService.WritePost(writePost);
    }

    @GetMapping
    public List<Post> ViewPost() {
        return viewPostService.ViewPost();
    }

    @GetMapping("/{postId}")
    public Post ViewPostById(@PathVariable Long postId) {
        return viewPostByIdService.ViewPostById(postId);
    }

    @PatchMapping("/{postId}")
    public void UpdatePost(@PathVariable Long postId, @RequestBody @Valid UpdatePost updatePost) {
        updatePostService.UpdatePost(postId, updatePost);
    }

    @DeleteMapping("/{postId}")
    public void DeletePost(@PathVariable Long postId) {
        postService.DeletePost(postId);
    }

    @GetMapping("/search")
    public List<ViewByCategoryResponse> SearchPost(@RequestParam String searchKeyword, @RequestParam String category){
        if(category.isEmpty()){
            log.info("필터 적용 X");
            return postService.SearchPost(searchKeyword);
        }else{
            log.info("필터 적용 O");
            return postService.SearchPost(searchKeyword,category);
        }
    }

    @GetMapping("/category")
    public List<ViewByCategoryResponse> ViewPostByCategory(@RequestParam String category){
        return postService.viewPostByCategory(category);
    }
}
