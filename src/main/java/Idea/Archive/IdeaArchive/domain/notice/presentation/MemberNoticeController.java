package Idea.Archive.IdeaArchive.domain.notice.presentation;

import Idea.Archive.IdeaArchive.domain.notice.presentation.dto.response.ViewNoticeByIdResponse;
import Idea.Archive.IdeaArchive.domain.notice.presentation.dto.response.ViewNoticeResponse;
import Idea.Archive.IdeaArchive.domain.notice.service.ViewNoticeByIdService;
import Idea.Archive.IdeaArchive.domain.notice.service.ViewNoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member/notice")
public class MemberNoticeController {

    private final ViewNoticeService viewNoticeService;
    private final ViewNoticeByIdService viewNoticeByIdService;

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
}
