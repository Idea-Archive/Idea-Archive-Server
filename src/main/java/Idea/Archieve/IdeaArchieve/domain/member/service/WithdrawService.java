package Idea.Archieve.IdeaArchieve.domain.member.service;


import Idea.Archieve.IdeaArchieve.domain.auth.entity.RefreshToken;
import Idea.Archieve.IdeaArchieve.domain.auth.exception.RefreshTokenNotFoundException;
import Idea.Archieve.IdeaArchieve.domain.auth.repository.RefreshTokenRepository;
import Idea.Archieve.IdeaArchieve.domain.member.Entity.Member;
import Idea.Archieve.IdeaArchieve.domain.member.exception.MisMatchPasswordException;
import Idea.Archieve.IdeaArchieve.domain.member.presentation.dto.request.WithdrawRequest;
import Idea.Archieve.IdeaArchieve.domain.member.repository.MemberRepository;
import Idea.Archieve.IdeaArchieve.domain.post.Entity.Heart;
import Idea.Archieve.IdeaArchieve.domain.post.repository.HeartRepository;
import Idea.Archieve.IdeaArchieve.domain.post.repository.PostRepository;
import Idea.Archieve.IdeaArchieve.global.util.MemberUtil;
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
    private final MemberUtil memberUtil;
    private final PasswordEncoder passwordEncoder;
    private final HeartRepository heartRepository;
    private final PostRepository postRepository;

    @Transactional
    public void execute(WithdrawRequest withdrawRequest){
        Member member = memberUtil.currentMember();
        RefreshToken refreshToken = refreshTokenRepository.findById(member.getEmail())
                        .orElseThrow(()->new RefreshTokenNotFoundException("존재하지 않은 리프레시 토큰입니다."));

        if(!passwordEncoder.matches(withdrawRequest.getPassword(), member.getPassword())){
            throw new MisMatchPasswordException("비밀번호가 일치하지 않습니다.");
        }

        List<Heart> hearts = heartRepository.findByMember_MemberId(member.getMemberId());
        for(int i=0;i<hearts.size();i++){
            hearts.get(i).getPost().updateHeart(hearts.get(i).getPost().getHeartCount()-1);
        }
        heartRepository.deleteByMember_MemberId(member.getMemberId());
        postRepository.deleteByMember_MemberId(member.getMemberId());
        memberRepository.delete(member);
        refreshTokenRepository.delete(refreshToken);
    }
}
