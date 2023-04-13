package Idea.Archive.IdeaArchive.domain.member.service;


import Idea.Archive.IdeaArchive.domain.auth.entity.RefreshToken;
import Idea.Archive.IdeaArchive.domain.auth.exception.RefreshTokenNotFoundException;
import Idea.Archive.IdeaArchive.domain.auth.repository.RefreshTokenRepository;
import Idea.Archive.IdeaArchive.domain.member.entity.Member;
import Idea.Archive.IdeaArchive.domain.member.exception.MemberNotFoundException;
import Idea.Archive.IdeaArchive.domain.member.exception.MisMatchPasswordException;
import Idea.Archive.IdeaArchive.domain.member.repository.MemberRepository;
import Idea.Archive.IdeaArchive.domain.post.entity.Heart;
import Idea.Archive.IdeaArchive.domain.post.repository.HeartRepository;
import Idea.Archive.IdeaArchive.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WithdrawService {

    private final MemberRepository memberRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final HeartRepository heartRepository;
    private final PostRepository postRepository;

    @Transactional
    public void execute(String email,String password) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberNotFoundException("유저가 존재하지 않습니다"));
        RefreshToken refreshToken = refreshTokenRepository.findById(member.getEmail())
                .orElseThrow(() -> new RefreshTokenNotFoundException("존재하지 않은 리프레시 토큰입니다."));

        if(!passwordEncoder.matches(password, member.getPassword())) {
            throw new MisMatchPasswordException("비밀번호가 일치하지 않습니다.");
        }

        List<Heart> hearts = heartRepository.findByMember_MemberId(member.getMemberId());
        for (int i = 0; i < hearts.size(); i++) {
            hearts.get(i).getPost().updateHeart(hearts.get(i).getPost().getHeartCount() - 1);
        }
        heartRepository.deleteByMember_MemberId(member.getMemberId());
        postRepository.deleteByMember_MemberId(member.getMemberId());
        memberRepository.delete(member);
        refreshTokenRepository.delete(refreshToken);
    }
}
