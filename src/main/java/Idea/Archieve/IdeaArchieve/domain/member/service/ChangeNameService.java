package Idea.Archieve.IdeaArchieve.domain.member.service;

import Idea.Archieve.IdeaArchieve.domain.member.entity.Member;
import Idea.Archieve.IdeaArchieve.domain.member.presentation.dto.request.ChangeNameRequest;
import Idea.Archieve.IdeaArchieve.global.util.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChangeNameService {

    private final MemberUtil memberUtil;


    @Transactional
    public void execute(ChangeNameRequest changeNameRequest){
        Member member = memberUtil.currentMember();
        member.updateName(changeNameRequest.getName());
    }
}
