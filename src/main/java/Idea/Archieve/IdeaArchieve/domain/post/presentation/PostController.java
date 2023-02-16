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
    private final ViewPostService viewPostService;
    private final ViewPostByIdService viewPostByIdService;
    private final UpdatePostService updatePostService;
    private final DeletePostService deletePostService;
    private final FilterPostService filterPostService;
    private final FilterPostByCategoryService filterPostByCategoryService;

    @PostMapping("/write")
    public ResponseEntity<Void> writePost(@RequestBody @Valid WritePostRequest writePostRequest) {
        writePostService.execute(writePostRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<Post>> viewPost() {
        viewPostService.execute();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> viewPostById(@PathVariable Long postId) {
        viewPostByIdService.execute(postId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{postId}")
    public ResponseEntity<Void> updatePost(@PathVariable Long postId, @RequestBody @Valid UpdatePost updatePost) {
        updatePostService.execute(postId, updatePost);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        deletePostService.execute(postId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Post>> searchPost(@RequestParam String searchKeyword, @RequestParam String category) {
        if(category.isEmpty()) {
            log.info("필터 적용 X");
            filterPostService.execute(searchKeyword);
        } else {
            log.info("필터 적용 O");
            filterPostService.execute(searchKeyword,category);
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/category")
    public ResponseEntity<List<Post>> viewPostByCategory(@RequestParam String category){
        filterPostByCategoryService.execute(category);
        return ResponseEntity.ok().build();
    }
}
