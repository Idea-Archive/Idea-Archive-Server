package Idea.Archive.IdeaArchive.domain.member.service;

import Idea.Archive.IdeaArchive.domain.auth.entity.RefreshToken;
import Idea.Archive.IdeaArchive.domain.auth.exception.RefreshTokenNotFoundException;
import Idea.Archive.IdeaArchive.domain.auth.repository.RefreshTokenRepository;
import Idea.Archive.IdeaArchive.domain.member.entity.Member;
import Idea.Archive.IdeaArchive.domain.member.exception.MemberNotFoundException;
import Idea.Archive.IdeaArchive.domain.member.exception.MisMatchPasswordException;
import Idea.Archive.IdeaArchive.domain.member.presentation.dto.request.WithdrawMemberRequest;
import Idea.Archive.IdeaArchive.domain.member.repository.MemberRepository;
import Idea.Archive.IdeaArchive.domain.heart.entity.Heart;
import Idea.Archive.IdeaArchive.domain.heart.repository.HeartRepository;
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

    @Transactional(rollbackFor = Exception.class)
    public void execute(WithdrawMemberRequest withdrawMemberRequest) {
        Member member = memberRepository.findByEmail(withdrawMemberRequest.getEmail())
                .orElseThrow(() -> new MemberNotFoundException());
        RefreshToken refreshToken = refreshTokenRepository.findById(member.getEmail())
                .orElseThrow(() -> new RefreshTokenNotFoundException());

        if (!passwordEncoder.matches(withdrawMemberRequest.getPassword(), member.getPassword())) {
            throw new MisMatchPasswordException();
        }

        List<Heart> hearts = heartRepository.findByMember(member);
        for (int i = 0; i < hearts.size(); i++) {
            hearts.get(i).getPost().updateHeart(hearts.get(i).getPost().getHeartCount() - 1);
        }
        heartRepository.deleteByMember(member);
        postRepository.deleteByMember(member);
        memberRepository.delete(member);
        refreshTokenRepository.delete(refreshToken);
    }
}
