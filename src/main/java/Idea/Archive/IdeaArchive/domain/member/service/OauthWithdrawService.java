package Idea.Archive.IdeaArchive.domain.member.service;

import Idea.Archive.IdeaArchive.domain.auth.entity.RefreshToken;
import Idea.Archive.IdeaArchive.domain.auth.exception.RefreshTokenNotFoundException;
import Idea.Archive.IdeaArchive.domain.auth.presentation.dto.request.OauthWithdrawMemberRequest;
import Idea.Archive.IdeaArchive.domain.auth.repository.RefreshTokenRepository;
import Idea.Archive.IdeaArchive.domain.member.entity.Member;
import Idea.Archive.IdeaArchive.domain.member.exception.MemberNotFoundException;
import Idea.Archive.IdeaArchive.domain.member.repository.MemberRepository;
import Idea.Archive.IdeaArchive.domain.heart.entity.Heart;
import Idea.Archive.IdeaArchive.domain.heart.repository.HeartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OauthWithdrawService {

    private final MemberRepository memberRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final HeartRepository heartRepository;

    @Transactional(rollbackFor = Exception.class)
    public void execute(OauthWithdrawMemberRequest oauthWithdrawMemberRequest) {
        Member member = memberRepository.findByEmail(oauthWithdrawMemberRequest.getEmail())
                .orElseThrow(MemberNotFoundException::new);

        RefreshToken refreshToken = refreshTokenRepository.findById(member.getEmail())
                .orElseThrow(RefreshTokenNotFoundException::new);

        List<Heart> hearts = heartRepository.findByMember(member);
        for (int i = 0; i < hearts.size(); i++) {
            hearts.get(i).getPost().updateHeart(hearts.get(i).getPost().getHeartCount() - 1);
        }
        memberRepository.delete(member);
        refreshTokenRepository.delete(refreshToken);
    }
}
