package Idea.Archive.IdeaArchive.domain.application.presentation;

import Idea.Archive.IdeaArchive.domain.application.service.ApplyApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/application")
public class ApplicationController {
    private final ApplyApplicationService applyApplicationService;

    @PostMapping("/{post_id}")
    public ResponseEntity<Void> applyApplication(@PathVariable Long post_id) {
        applyApplicationService.execute(post_id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
