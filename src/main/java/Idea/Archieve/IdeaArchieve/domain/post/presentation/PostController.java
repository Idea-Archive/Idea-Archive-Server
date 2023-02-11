package Idea.Archieve.IdeaArchieve.domain.post.presentation;

import Idea.Archieve.IdeaArchieve.domain.post.Entity.Post;
import Idea.Archieve.IdeaArchieve.domain.post.presentation.dto.request.UpdatePost;
import Idea.Archieve.IdeaArchieve.domain.post.presentation.dto.request.WritePost;
import Idea.Archieve.IdeaArchieve.domain.post.service.PostService;
import Idea.Archieve.IdeaArchieve.domain.post.service.ViewPostByIdService;
import Idea.Archieve.IdeaArchieve.domain.post.service.ViewPostService;
import Idea.Archieve.IdeaArchieve.domain.post.service.WritePostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
    public void UpdatePost(@PathVariable Long postId, @RequestBody UpdatePost updatePost) {
        postService.UpdatePost(postId, updatePost);
    }

    @DeleteMapping("/{postId}")
    public void DeletePost(@PathVariable Long postId) {
        postService.DeletePost(postId);
    }

    @GetMapping("/search")
    public List<Post> SearchPost(@RequestParam String searchKeyword, @RequestParam String category){
        if(category.equals("null")){
            log.info("필터 적용 X");
            return postService.SearchPost(searchKeyword);
        }else{
            log.info("필터 적용 O");
            return postService.SearchPost(searchKeyword,category);
        }
    }

    @GetMapping("/category")
    public List<Post> ViewPostByCategory(@RequestParam String category){
        return postService.viewPostByCategory(category);
    }
}
