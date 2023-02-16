package Idea.Archieve.IdeaArchieve.domain.post.presentation;

import Idea.Archieve.IdeaArchieve.domain.post.Entity.Post;
import Idea.Archieve.IdeaArchieve.domain.post.presentation.dto.request.UpdatePost;
import Idea.Archieve.IdeaArchieve.domain.post.presentation.dto.request.WritePostRequest;
import Idea.Archieve.IdeaArchieve.domain.post.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private final DeletePostService deletePostService;

    @PostMapping("write")
    public ResponseEntity<Void> WritePost(@RequestBody @Valid WritePostRequest writePostRequest) {
        writePostService.execute(writePostRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<Post>> ViewPost() {
        viewPostService.execute();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> ViewPostById(@PathVariable Long postId) {
        viewPostByIdService.execute(postId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{postId}")
    public ResponseEntity<Void> UpdatePost(@PathVariable Long postId, @RequestBody @Valid UpdatePost updatePost) {
        updatePostService.execute(postId, updatePost);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> DeletePost(@PathVariable Long postId) {
        deletePostService.execute(postId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Post>> SearchPost(@RequestParam String searchKeyword, @RequestParam String category) {
        if(category.equals(null)) {
            log.info("필터 적용 X");
            postService.SearchPost(searchKeyword);
        } else {
            log.info("필터 적용 O");
            postService.SearchPost(searchKeyword,category);
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/category")
    public ResponseEntity<List<Post>> ViewPostByCategory(@RequestParam String category){
        postService.viewPostByCategory(category);
        return ResponseEntity.ok().build();
    }
}
