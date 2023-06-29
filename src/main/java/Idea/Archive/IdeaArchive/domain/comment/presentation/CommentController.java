package Idea.Archive.IdeaArchive.domain.comment.presentation;

import Idea.Archive.IdeaArchive.domain.comment.presentation.dto.request.ModifyCommentRequest;
import Idea.Archive.IdeaArchive.domain.comment.presentation.dto.request.WriteCommentRequest;
import Idea.Archive.IdeaArchive.domain.comment.service.DeleteCommentService;
import Idea.Archive.IdeaArchive.domain.comment.service.ModifyCommentService;
import Idea.Archive.IdeaArchive.domain.comment.service.WriteCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class CommentController {

    private final WriteCommentService writeCommentService;
    private final ModifyCommentService modifyCommentService;
    private final DeleteCommentService deleteCommentService;

    @PostMapping("/comment/{post_id}")
    public ResponseEntity<Void> writeComment(@PathVariable("post_id") Long postId, @RequestBody @Valid WriteCommentRequest request) {
        writeCommentService.execute(postId, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/comment/{comment_id}")
    public ResponseEntity<Void> modifyComment(@PathVariable("comment_id") Long commentId, @RequestBody @Valid ModifyCommentRequest request) {
        modifyCommentService.execute(commentId, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/comment/{comment_id}")
    public ResponseEntity<Void> DeleteComment(@PathVariable("comment_id") Long commentId) {
        deleteCommentService.execute(commentId);
        return ResponseEntity.ok().build();
    }
}
