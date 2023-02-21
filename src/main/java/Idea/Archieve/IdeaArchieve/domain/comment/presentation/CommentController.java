package Idea.Archieve.IdeaArchieve.domain.comment.presentation;

import Idea.Archieve.IdeaArchieve.domain.comment.presentation.dto.request.WriteCommentRequest;
import Idea.Archieve.IdeaArchieve.domain.comment.service.WriteCommentService;
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

    @PostMapping("/{postId}/write")
    public ResponseEntity<Void> writeComment(@PathVariable Long postId, @RequestBody @Valid WriteCommentRequest request) {
        writeCommentService.execute(postId, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
