package Idea.Archive.IdeaArchive.domain.post.presentation;

import Idea.Archive.IdeaArchive.domain.heart.service.InsertHeartService;
import Idea.Archive.IdeaArchive.domain.post.presentation.dto.request.CategoryRequest;
import Idea.Archive.IdeaArchive.domain.post.presentation.dto.request.ModifyPostRequest;
import Idea.Archive.IdeaArchive.domain.post.presentation.dto.request.WritePostRequest;
import Idea.Archive.IdeaArchive.domain.post.presentation.dto.response.*;
import Idea.Archive.IdeaArchive.domain.post.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final WritePostService writePostService;
    private final ViewPostService viewPostService;
    private final ViewPostByIdService viewPostByIdService;
    private final ModifyPostService modifyPostService;
    private final DeletePostService deletePostService;
    private final SearchPostService searchPostService;
    private final ViewPostByCategoryService viewPostByCategoryService;
    private final InsertHeartService insertHeartService;
    private final SharePostService sharePostService;
    private final ViewPopularPostService viewPopularPostService;

    @PostMapping("/write")
    public ResponseEntity<Void> writePost(@RequestBody @Valid WritePostRequest writePostRequest) {
        writePostService.execute(writePostRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<ViewPostResponse>> viewNotice() {
        List<ViewPostResponse> response = viewPostService.execute();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{post_id}")
    public ResponseEntity<ViewPostByIdResponse> viewPostById(@PathVariable("post_id") Long postId) {
        ViewPostByIdResponse response = viewPostByIdService.execute(postId);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{post_id}")
    public ResponseEntity<Void> updatePost(@PathVariable("post_id") Long postId, @RequestBody @Valid ModifyPostRequest modifyPostRequest) {
        modifyPostService.execute(postId, modifyPostRequest);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{post_id}")
    public ResponseEntity<Void> deletePost(@PathVariable("post_id") Long postId) {
        deletePostService.execute(postId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/search")
    public ResponseEntity<List<ViewPostResponse>> searchPost(@RequestParam String keyword, @RequestBody CategoryRequest categoryRequest) {
        List<ViewPostResponse> response = searchPostService.execute(keyword, categoryRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/category")
    public ResponseEntity<List<ViewPostResponse>> viewPostByCategory(@RequestBody CategoryRequest categoryRequest) {
        List<ViewPostResponse> response = viewPostByCategoryService.execute(categoryRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("{post_id}/heart")
    public ResponseEntity<Void> insertHeart(@PathVariable("post_id") Long postId) {
        insertHeartService.execute(postId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/share/{post_id}")
    public ResponseEntity<SharePostResponse> sharePost(@PathVariable("post_id") Long postId) {
        SharePostResponse postUrl = sharePostService.execute(postId);
        return ResponseEntity.ok(postUrl);
    }

    @GetMapping("/popular")
    public ResponseEntity<List<ViewPostResponse>> viewPostByHeart() {
        List<ViewPostResponse> posts = viewPopularPostService.execute();
        return ResponseEntity.ok(posts);
    }
}
