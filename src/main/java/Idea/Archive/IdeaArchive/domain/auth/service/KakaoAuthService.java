package Idea.Archive.IdeaArchive.domain.auth.service;

import Idea.Archive.IdeaArchive.domain.auth.entity.RefreshToken;
import Idea.Archive.IdeaArchive.domain.auth.presentation.dto.response.MemberLoginResponse;
import Idea.Archive.IdeaArchive.domain.auth.repository.RefreshTokenRepository;
import Idea.Archive.IdeaArchive.domain.member.entity.Member;
import Idea.Archive.IdeaArchive.domain.member.repository.MemberRepository;
import Idea.Archive.IdeaArchive.global.filter.role.Role;
import Idea.Archive.IdeaArchive.global.img.DefaultImage;
import Idea.Archive.IdeaArchive.global.security.KakaoAuthProperties;
import Idea.Archive.IdeaArchive.global.security.jwt.TokenProvider;
import Idea.Archive.IdeaArchive.global.security.jwt.properties.JwtProperties;
import Idea.Archive.IdeaArchive.infrastructure.feign.client.KakaoAuth;
import Idea.Archive.IdeaArchive.infrastructure.feign.client.KakaoInfo;
import Idea.Archive.IdeaArchive.infrastructure.feign.dto.request.KakaoCodeRequest;
import Idea.Archive.IdeaArchive.infrastructure.feign.dto.response.KakaoInfoResponse;
import Idea.Archive.IdeaArchive.infrastructure.feign.dto.response.KakaoTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class KakaoAuthService {

    private final KakaoAuth kakaoAuth;
    private final KakaoInfo kakaoInfo;
    private final KakaoAuthProperties kakaoAuthProperties;
    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtProperties jwtProperties;

    private void createUser(String email, String name) {
        if (memberRepository.findByEmail(email).isEmpty()) {
            memberRepository.save(
                    Member.builder()
                            .email(email)
                            .name(name)
                            .role(Role.MEMBER)
                            .profileImageUrl(DefaultImage.MEMBER_PROFILE_IMAGE)
                            .build());
        }
    }

    @Transactional
    public MemberLoginResponse execute(String code) {

        KakaoTokenResponse kakaoTokenResponse = kakaoAuth.kakaoAuth(
                KakaoCodeRequest.builder()
                        .code(URLDecoder.decode(code, StandardCharsets.UTF_8))
                        .clientId(kakaoAuthProperties.getClientId())
                        .clientSecret(kakaoAuthProperties.getClientSecret())
                        .redirectUrl(kakaoAuthProperties.getRedirectUrl())
                        .build()
        );

        KakaoInfoResponse kakaoInfoResponse = kakaoInfo.kakaoInfo(kakaoTokenResponse.getAccess_token());

        String email = kakaoInfoResponse.getEmail();
        String name = kakaoInfoResponse.getName();

        String refreshToken = tokenProvider.generatedRefreshToken(email);
        String jwtAccessToken = tokenProvider.generatedAccessToken(email);

        refreshTokenRepository.save(
                RefreshToken.builder()
                        .email(email)
                        .refreshToken(refreshToken)
                        .expiredAt(tokenProvider.getACCESS_TOKEN_EXPIRE_TIME())
                        .build());

        createUser(email, name);

        return MemberLoginResponse.builder()
                .accessToken(jwtAccessToken)
                .refreshToken(refreshToken)
                .expiredAt(tokenProvider.getExpiredAtToken(jwtAccessToken, jwtProperties.getAccessSecret()))
                .build();

    }
}
