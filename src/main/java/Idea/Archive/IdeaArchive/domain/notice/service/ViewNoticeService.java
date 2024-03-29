package Idea.Archive.IdeaArchive.domain.notice.service;

import Idea.Archive.IdeaArchive.domain.notice.entity.Notice;
import Idea.Archive.IdeaArchive.domain.notice.presentation.dto.response.ViewNoticeResponse;
import Idea.Archive.IdeaArchive.domain.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ViewNoticeService {

    private final NoticeRepository noticeRepository;

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<ViewNoticeResponse> execute() {
        List<Notice> noticeResponses = noticeRepository.findAll();
        return noticeResponses.stream()
                .map(notice -> ViewNoticeResponse.builder()
                        .noticeId(notice.getNoticeId())
                        .title(notice.getTitle())
                        .content(notice.getContent())
                        .createdDate(notice.getCreatedDate())
                        .build())
                .collect(Collectors.toList());
    }
}
