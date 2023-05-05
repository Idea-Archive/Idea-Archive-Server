package Idea.Archive.IdeaArchive.domain.auth.service;

import Idea.Archive.IdeaArchive.domain.auth.entity.RefreshToken;
import Idea.Archive.IdeaArchive.domain.auth.presentation.dto.response.MemberLoginResponse;
import Idea.Archive.IdeaArchive.domain.auth.repository.RefreshTokenRepository;
import Idea.Archive.IdeaArchive.domain.member.entity.Member;
import Idea.Archive.IdeaArchive.domain.member.repository.MemberRepository;
import Idea.Archive.IdeaArchive.domain.member.enums.Role;
import Idea.Archive.IdeaArchive.global.oauth.KakaoAuthProperties;
import Idea.Archive.IdeaArchive.global.security.jwt.TokenProvider;
import Idea.Archive.IdeaArchive.infrastructure.feign.client.KakaoAuth;
import Idea.Archive.IdeaArchive.infrastructure.feign.client.KakaoInfo;
import Idea.Archive.IdeaArchive.infrastructure.feign.dto.response.KakaoInfoResponse;
import Idea.Archive.IdeaArchive.infrastructure.feign.dto.response.KakaoTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class KakaoAuthService {

    private final KakaoAuth kakaoAuth;
    private final KakaoInfo kakaoInfo;
    private final KakaoAuthProperties kakaoAuthProperties;
    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional(rollbackFor = Exception.class)
    public MemberLoginResponse execute(String code) {

        KakaoTokenResponse kakaoTokenResponse = kakaoAuth.kakaoAuth(
                code,
                kakaoAuthProperties.getClientId(),
                kakaoAuthProperties.getClientSecret(),
                kakaoAuthProperties.getRedirectUri(),
                "authorization_code"
        );

        KakaoInfoResponse kakaoInfoResponse = kakaoInfo.kakaoInfo("Bearer " + kakaoTokenResponse.getAccess_token());

        String email = kakaoInfoResponse.getKakao_account().getEmail();
        String name = kakaoInfoResponse.getKakao_account().getProfile().getNickname();

        String refreshToken = tokenProvider.generatedRefreshToken(email);
        String accessToken = tokenProvider.generatedAccessToken(email);

        refreshTokenRepository.save(
                RefreshToken.builder()
                        .email(email)
                        .refreshToken(refreshToken)
                        .expiredAt(tokenProvider.getACCESS_TOKEN_EXPIRE_TIME())
                        .build());

        createUser(email, name);

        return MemberLoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .expiredAt(tokenProvider.getExpiredAtToken())
                .build();
    }

    private void createUser(String email, String name) {
        if (memberRepository.findByEmail(email).isEmpty()) {
            memberRepository.save(
                    Member.builder()
                            .email(email)
                            .name(name)
                            .role(Role.MEMBER)
                            .password(null)
                            .profileImageUrl(null)
                            .build());
        }
    }
}
