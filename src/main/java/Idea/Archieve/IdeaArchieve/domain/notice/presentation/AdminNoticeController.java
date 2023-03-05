package Idea.Archieve.IdeaArchieve.domain.notice.presentation;

import Idea.Archieve.IdeaArchieve.domain.notice.presentation.dto.request.ModifyNoticeRequest;
import Idea.Archieve.IdeaArchieve.domain.notice.presentation.dto.request.WriteNoticeRequest;
import Idea.Archieve.IdeaArchieve.domain.notice.presentation.dto.response.ViewNoticeByIdResponse;
import Idea.Archieve.IdeaArchieve.domain.notice.presentation.dto.response.ViewNoticeResponse;
import Idea.Archieve.IdeaArchieve.domain.notice.service.*;
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

    private final WriteNoticeService writeNoticeService;
    private final ViewNoticeService viewNoticeService;
    private final DeleteNoticeService deleteNoticeService;
    private final ModifyNoticeService modifyNoticeService;
    private final ViewNoticeByIdService viewNoticeByIdService;

    @PostMapping("/write")
    public ResponseEntity<Void> writeNotice(@RequestBody @Valid WriteNoticeRequest writeNoticeRequest){
        writeNoticeService.execute(writeNoticeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping
    public ResponseEntity<List<ViewNoticeResponse>> viewNotice(){
        List<ViewNoticeResponse> viewNoticeResponses = viewNoticeService.execute();
        return ResponseEntity.ok(viewNoticeResponses);
    }
    @DeleteMapping("{noticeId}")
    public ResponseEntity<Void> deleteNotice(@PathVariable Long noticeId){
        deleteNoticeService.execute(noticeId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @PatchMapping("{noticeId}")
    public ResponseEntity<Void> modifyNotice(@PathVariable Long noticeId, @RequestBody @Valid ModifyNoticeRequest modifyNoticeRequest){
        modifyNoticeService.execute(noticeId,modifyNoticeRequest);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @GetMapping("{noticeId}")
    public ResponseEntity<ViewNoticeByIdResponse> viewNoticeById(@PathVariable Long noticeId){
        ViewNoticeByIdResponse viewNoticeResponses = viewNoticeByIdService.execute(noticeId);
        return ResponseEntity.ok(viewNoticeResponses);
    }
}
