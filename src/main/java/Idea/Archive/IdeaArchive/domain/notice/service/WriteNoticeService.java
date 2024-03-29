package Idea.Archive.IdeaArchive.domain.notice.service;

import Idea.Archive.IdeaArchive.domain.member.entity.Member;
import Idea.Archive.IdeaArchive.domain.notice.entity.Notice;
import Idea.Archive.IdeaArchive.domain.notice.exception.NotQualifiedWriteNoticeException;
import Idea.Archive.IdeaArchive.domain.notice.presentation.dto.request.WriteNoticeRequest;
import Idea.Archive.IdeaArchive.domain.notice.repository.NoticeRepository;
import Idea.Archive.IdeaArchive.domain.member.enums.Role;
import Idea.Archive.IdeaArchive.global.util.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WriteNoticeService {

    private final NoticeRepository noticeRepository;
    private final MemberUtil memberUtil;

    @Transactional(rollbackFor = Exception.class)
    public void execute(WriteNoticeRequest writeNoticeRequest) {
        Member member = memberUtil.currentMember();
        if (member.getRole() != Role.MEMBER) {
            throw new NotQualifiedWriteNoticeException();
        }
        Notice notice = Notice.builder()
                .title(writeNoticeRequest.getTitle())
                .content(writeNoticeRequest.getContent())
                .member(member)
                .build();
        noticeRepository.save(notice);
    }
}
