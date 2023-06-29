package Idea.Archive.IdeaArchive.domain.notice.presentation;

import Idea.Archive.IdeaArchive.domain.notice.presentation.dto.request.ModifyNoticeRequest;
import Idea.Archive.IdeaArchive.domain.notice.presentation.dto.request.WriteNoticeRequest;
import Idea.Archive.IdeaArchive.domain.notice.presentation.dto.response.ViewNoticeByIdResponse;
import Idea.Archive.IdeaArchive.domain.notice.presentation.dto.response.ViewNoticeResponse;
import Idea.Archive.IdeaArchive.domain.notice.service.ModifyNoticeService;
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
    private final ModifyNoticeService modifyNoticeService;

    @GetMapping
    public ResponseEntity<List<ViewNoticeResponse>> viewNotice() {
        List<ViewNoticeResponse> response = viewNoticeService.execute();
        return ResponseEntity.ok(response);
    }

    @GetMapping("{notice_id}")
    public ResponseEntity<ViewNoticeByIdResponse> viewNoticeById(@PathVariable("notice_id") Long noticeId) {
        ViewNoticeByIdResponse response = viewNoticeByIdService.execute(noticeId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/write")
    public ResponseEntity<Void> writeNotice(@RequestBody @Valid WriteNoticeRequest writeNoticeRequest) {
        writeNoticeService.execute(writeNoticeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("{notice_id}")
    public ResponseEntity<Void> modifyNotice(@PathVariable("notice_id") Long noticeId, @RequestBody @Valid ModifyNoticeRequest modifyNoticeRequest) {
        modifyNoticeService.execute(noticeId, modifyNoticeRequest);
        return ResponseEntity.ok().build();
    }
}
