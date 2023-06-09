package Idea.Archive.IdeaArchive.domain.member.service;

import Idea.Archive.IdeaArchive.domain.member.presentation.dto.request.ChangeNameRequest;
import Idea.Archive.IdeaArchive.domain.member.entity.Member;
import Idea.Archive.IdeaArchive.global.util.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Service
@RequiredArgsConstructor
public class ChangeNameService {

    private final MemberUtil memberUtil;

    @Transactional(rollbackFor = Exception.class)
    public void execute(@RequestBody @Valid ChangeNameRequest changeNameRequest) {
        Member member = memberUtil.currentMember();
        member.updateName(changeNameRequest.getName());
    }
}
