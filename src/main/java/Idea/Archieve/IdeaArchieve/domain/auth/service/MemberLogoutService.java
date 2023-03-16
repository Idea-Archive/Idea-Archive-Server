package Idea.Archieve.IdeaArchieve.domain.auth.service;


import Idea.Archieve.IdeaArchieve.domain.auth.entity.BlackList;
import Idea.Archieve.IdeaArchieve.domain.auth.entity.RefreshToken;
import Idea.Archieve.IdeaArchieve.domain.auth.exception.BlackListAlreadyExistException;
import Idea.Archieve.IdeaArchieve.domain.auth.exception.RefreshTokenNotFoundException;
import Idea.Archieve.IdeaArchieve.domain.auth.repository.BlackListRepository;
import Idea.Archieve.IdeaArchieve.domain.auth.repository.RefreshTokenRepository;
import Idea.Archieve.IdeaArchieve.domain.member.entity.Member;
import Idea.Archieve.IdeaArchieve.global.util.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberLogoutService {

    private final BlackListRepository blackListRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final MemberUtil memberUtil;
    private final RedisTemplate redisTemplate;


    public void execute(String accessToken){
        Member member = memberUtil.currentMember();
        RefreshToken refreshToken = refreshTokenRepository.findById(member.getEmail())
                .orElseThrow(()->new RefreshTokenNotFoundException("RefreshToken을 찾을 수 없습니다."));
        refreshTokenRepository.delete(refreshToken);
        saveBlackList(accessToken,member.getEmail());
    }

    private void saveBlackList(String accessToken, String email) {
        if(redisTemplate.opsForValue().get(accessToken)!=null){
            throw new BlackListAlreadyExistException("블랙리스트에 이미 등록되어있습니다.");
        }
        BlackList blackList = BlackList.builder()
                .email(email)
                .accessToken(accessToken)
                .build();
        blackListRepository.save(blackList);
    }


}