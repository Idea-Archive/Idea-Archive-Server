package Idea.Archive.IdeaArchive.domain.auth.service;

import Idea.Archive.IdeaArchive.domain.auth.entity.RefreshToken;
import Idea.Archive.IdeaArchive.domain.auth.presentation.dto.response.MemberLoginResponse;
import Idea.Archive.IdeaArchive.domain.auth.repository.RefreshTokenRepository;
import Idea.Archive.IdeaArchive.domain.member.entity.Member;
import Idea.Archive.IdeaArchive.domain.member.repository.MemberRepository;
import Idea.Archive.IdeaArchive.global.filter.role.Role;
import Idea.Archive.IdeaArchive.global.security.GoogleAuthProperties;
import Idea.Archive.IdeaArchive.global.security.jwt.TokenProvider;
import Idea.Archive.IdeaArchive.global.security.jwt.properties.JwtProperties;
import Idea.Archive.IdeaArchive.infrastructure.feign.client.GoogleAuth;
import Idea.Archive.IdeaArchive.infrastructure.feign.client.GoogleInfo;
import Idea.Archive.IdeaArchive.infrastructure.feign.dto.request.GoogleCodeRequest;
import Idea.Archive.IdeaArchive.infrastructure.feign.dto.response.GoogleInfoResponse;
import Idea.Archive.IdeaArchive.infrastructure.feign.dto.response.GoogleTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class GoogleAuthService {

    private final GoogleAuth googleAuth;
    private final GoogleInfo googleInfo;
    private final GoogleAuthProperties googleAuthProperties;
    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;
    private final JwtProperties jwtProperties;
    private final RefreshTokenRepository refreshTokenRepository;

    private void createUser(String email, String name) {
        if (memberRepository.findByEmail(email).isEmpty()) {
            memberRepository.save(
                    Member.builder()
                            .email(email)
                            .name(name)
                            .role(Role.MEMBER)
                            .profileImageUrl(null)
                            .build());
        }
    }

    @Transactional
    public MemberLoginResponse execute(String code) {

        GoogleTokenResponse googleTokenResponse = googleAuth.googleAuth(
            GoogleCodeRequest.builder()
                        .code(URLDecoder.decode(code, StandardCharsets.UTF_8))
                        .clientId(googleAuthProperties.getClientId())
                        .clientSecret(googleAuthProperties.getClientSecret())
                        .redirectUri(googleAuthProperties.getRedirectUrl())
                        .build()
        );

        GoogleInfoResponse googleInfoResponse = googleInfo.googleInfo(googleTokenResponse.getAccess_token());

        String email = googleInfoResponse.getEmail();
        String name = googleInfoResponse.getName();

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