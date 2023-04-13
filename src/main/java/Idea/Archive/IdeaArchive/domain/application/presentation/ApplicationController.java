package Idea.Archive.IdeaArchive.domain.application.presentation;

import Idea.Archive.IdeaArchive.domain.application.presentation.dto.response.ApplicationResponse;
import Idea.Archive.IdeaArchive.domain.application.service.ApplicationListService;
import Idea.Archive.IdeaArchive.domain.application.service.ApplyApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/application")
public class ApplicationController {
    private final ApplyApplicationService applyApplicationService;
    private final ApplicationListService applicationListService;

    @PostMapping("/{post_id}")
    public ResponseEntity<Void> applyApplication(@PathVariable Long post_id) {
        applyApplicationService.execute(post_id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{post_id}")
    public ResponseEntity<List<ApplicationResponse>> listApplication(@PathVariable Long post_id) {
        List<ApplicationResponse> applications = applicationListService.execute(post_id);
        return ResponseEntity.ok(applications);
    }

}
