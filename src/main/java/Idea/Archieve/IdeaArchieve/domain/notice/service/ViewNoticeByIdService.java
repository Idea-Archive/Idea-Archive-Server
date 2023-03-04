package Idea.Archieve.IdeaArchieve.domain.notice.service;

import Idea.Archieve.IdeaArchieve.domain.notice.entity.Notice;
import Idea.Archieve.IdeaArchieve.domain.notice.exception.NoticeNotFoundException;
import Idea.Archieve.IdeaArchieve.domain.notice.presentation.dto.response.ViewNoticeByIdResponse;
import Idea.Archieve.IdeaArchieve.domain.notice.presentation.dto.response.ViewNoticeResponse;
import Idea.Archieve.IdeaArchieve.domain.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class ViewNoticeByIdService {

    private final NoticeRepository noticeRepository;

    @Transactional
    public ViewNoticeByIdResponse execute(Long noticeId){
       Notice notice = noticeRepository.findById(noticeId)
               .orElseThrow(()-> new NoticeNotFoundException("공지글이 존재하지 않습니다"));

       return ViewNoticeByIdResponse.builder()
               .id(noticeId)
               .title(notice.getTitle())
               .content(notice.getContent())
               .build();

    }
}
