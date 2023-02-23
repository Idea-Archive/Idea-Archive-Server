package Idea.Archieve.IdeaArchieve.global.util;

import Idea.Archieve.IdeaArchieve.domain.member.entity.Member;
import Idea.Archieve.IdeaArchieve.domain.member.exception.MemberNotFoundException;
import Idea.Archieve.IdeaArchieve.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MemberUtil {

    private final MemberRepository memberRepository;

    public Member currentMember(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberNotFoundException("존재하지 않는 회원입니다."));
    }
}