package Idea.Archieve.IdeaArchieve.domain.member.service;

import Idea.Archieve.IdeaArchieve.domain.member.Entity.Member;
import Idea.Archieve.IdeaArchieve.domain.member.exception.MemberNotFoundException;
import Idea.Archieve.IdeaArchieve.domain.member.presentation.dto.response.MyPageResponse;
import Idea.Archieve.IdeaArchieve.domain.member.repository.MemberRepository;
import Idea.Archieve.IdeaArchieve.global.util.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyPageService {

    private final MemberRepository memberRepository;
    private final MemberUtil memberUtil;

    public MyPageResponse execute(){
        Member currentMember = memberUtil.currentMember();
        Member member = memberRepository.findByEmail(currentMember.getEmail())
                .orElseThrow(()->new MemberNotFoundException("존재하지 않는 회원입니다"));
        return MyPageResponse.builder()
                .email(member.getEmail())
                .password(member.getPassword())
                .name(member.getName())
                .build();
    }

}
