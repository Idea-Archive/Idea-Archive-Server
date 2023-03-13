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

    @Transactional
    public List<ViewNoticeResponse> execute() {
        List<Notice> noticeResponses = noticeRepository.findAll();
        return noticeResponses.stream()
                .map(notice -> ViewNoticeResponse.builder()
                        .id(notice.getId())
                        .title(notice.getTitle())
                        .content(notice.getContent())
                        .build())
                .collect(Collectors.toList());
    }
}
