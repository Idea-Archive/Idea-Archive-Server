package Idea.Archive.IdeaArchive.domain.notice.service;

import Idea.Archive.IdeaArchive.domain.member.entity.Member;
import Idea.Archive.IdeaArchive.domain.notice.entity.Notice;
import Idea.Archive.IdeaArchive.domain.notice.exception.NotQualifiedDeleteNoticeException;
import Idea.Archive.IdeaArchive.domain.notice.exception.NoticeNotFoundException;
import Idea.Archive.IdeaArchive.domain.notice.presentation.dto.request.ModifyNoticeRequest;
import Idea.Archive.IdeaArchive.domain.notice.repository.NoticeRepository;
import Idea.Archive.IdeaArchive.global.util.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ModifyNoticeService {

    private final NoticeRepository noticeRepository;
    private final MemberUtil memberUtil;

    @Transactional(rollbackFor = Exception.class)
    public void execute(Long noticeId, ModifyNoticeRequest modifyNoticeRequest) {
        Member member = memberUtil.currentMember();
        Notice notice = noticeRepository.findById(noticeId)
                .orElseThrow(() -> new NoticeNotFoundException("공지글이 존재하지 않습니다."));
        if (member.getMemberId() != notice.getMember().getMemberId()) {
            throw new NotQualifiedDeleteNoticeException("공지글을 삭제할 권한이 없습니다.");
        }
        notice.update(modifyNoticeRequest.getTitle(), modifyNoticeRequest.getContent());
        noticeRepository.save(notice);
    }
}
