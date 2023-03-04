package Idea.Archieve.IdeaArchieve.domain.notice.service;

import Idea.Archieve.IdeaArchieve.domain.member.entity.Member;
import Idea.Archieve.IdeaArchieve.domain.notice.entity.Notice;
import Idea.Archieve.IdeaArchieve.domain.notice.exception.NotQualifiedWriteNoticeException;
import Idea.Archieve.IdeaArchieve.domain.notice.presentation.dto.request.WriteNoticeRequest;
import Idea.Archieve.IdeaArchieve.domain.notice.repository.NoticeRepository;
import Idea.Archieve.IdeaArchieve.global.filter.role.Role;
import Idea.Archieve.IdeaArchieve.global.util.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WriteNoticeService {

    private final NoticeRepository noticeRepository;
    private final MemberUtil memberUtil;


    @Transactional
    public void execute(WriteNoticeRequest writeNoticeRequest){
        Member member = memberUtil.currentMember();
        if(member.getRole() != Role.ADMIN){
            throw new NotQualifiedWriteNoticeException("공지글을 작성할 권한이 없습니다");
        }
        Notice notice = Notice.builder()
                .title(writeNoticeRequest.getTitle())
                .content(writeNoticeRequest.getContent())
                .member(member)
                .build();
        noticeRepository.save(notice);
    }
}
