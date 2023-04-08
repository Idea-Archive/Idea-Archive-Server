package Idea.Archive.IdeaArchive.domain.notice.presentation;

import Idea.Archive.IdeaArchive.domain.notice.presentation.dto.request.WriteNoticeRequest;
import Idea.Archive.IdeaArchive.domain.notice.presentation.dto.response.ViewNoticeByIdResponse;
import Idea.Archive.IdeaArchive.domain.notice.presentation.dto.response.ViewNoticeResponse;
import Idea.Archive.IdeaArchive.domain.notice.service.ViewNoticeByIdService;
import Idea.Archive.IdeaArchive.domain.notice.service.ViewNoticeService;
import Idea.Archive.IdeaArchive.domain.notice.service.WriteNoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/notice")
public class AdminNoticeController {

    private final ViewNoticeService viewNoticeService;
    private final ViewNoticeByIdService viewNoticeByIdService;
    private final WriteNoticeService writeNoticeService;

    @GetMapping
    public ResponseEntity<List<ViewNoticeResponse>> viewNotice() {
        List<ViewNoticeResponse> viewNoticeResponses = viewNoticeService.execute();
        return ResponseEntity.ok(viewNoticeResponses);
    }

    @GetMapping("{noticeId}")
    public ResponseEntity<ViewNoticeByIdResponse> viewNoticeById(@PathVariable Long noticeId) {
        ViewNoticeByIdResponse viewNoticeResponse = viewNoticeByIdService.execute(noticeId);
        return ResponseEntity.ok(viewNoticeResponse);
    }

    @PostMapping("/write")
    public ResponseEntity<Void> writeNotice(@RequestBody @Valid WriteNoticeRequest writeNoticeRequest) {
        writeNoticeService.execute(writeNoticeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
