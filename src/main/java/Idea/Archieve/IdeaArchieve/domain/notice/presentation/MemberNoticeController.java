package Idea.Archieve.IdeaArchieve.domain.notice.presentation;

import Idea.Archieve.IdeaArchieve.domain.notice.presentation.dto.response.ViewNoticeByIdResponse;
import Idea.Archieve.IdeaArchieve.domain.notice.presentation.dto.response.ViewNoticeResponse;
import Idea.Archieve.IdeaArchieve.domain.notice.service.ViewNoticeByIdService;
import Idea.Archieve.IdeaArchieve.domain.notice.service.ViewNoticeService;
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
    public ResponseEntity<List<ViewNoticeResponse>> viewNotice(){
        List<ViewNoticeResponse> viewNoticeResponses = viewNoticeService.execute();
        return ResponseEntity.ok(viewNoticeResponses);
    }
    @GetMapping("{noticeId}")
    public ResponseEntity<ViewNoticeByIdResponse> viewNoticeById(@PathVariable Long noticeId){
        ViewNoticeByIdResponse viewNoticeResponses = viewNoticeByIdService.execute(noticeId);
        return ResponseEntity.ok(viewNoticeResponses);
    }
}
