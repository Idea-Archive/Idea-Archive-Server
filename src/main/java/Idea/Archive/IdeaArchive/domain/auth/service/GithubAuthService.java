package Idea.Archive.IdeaArchive.domain.auth.service;

import Idea.Archive.IdeaArchive.domain.auth.entity.RefreshToken;
import Idea.Archive.IdeaArchive.domain.auth.presentation.dto.response.MemberLoginResponse;
import Idea.Archive.IdeaArchive.domain.auth.repository.RefreshTokenRepository;
import Idea.Archive.IdeaArchive.domain.member.entity.Member;
import Idea.Archive.IdeaArchive.domain.member.repository.MemberRepository;
import Idea.Archive.IdeaArchive.global.filter.role.Role;
import Idea.Archive.IdeaArchive.global.img.DefaultImage;
import Idea.Archive.IdeaArchive.global.security.GithubAuthProperties;
import Idea.Archive.IdeaArchive.global.security.jwt.TokenProvider;
import Idea.Archive.IdeaArchive.global.security.jwt.properties.JwtProperties;
import Idea.Archive.IdeaArchive.infrastructure.feign.client.GithubAuth;
import Idea.Archive.IdeaArchive.infrastructure.feign.client.GithubEmailInfo;
import Idea.Archive.IdeaArchive.infrastructure.feign.client.GithubNameInfo;
import Idea.Archive.IdeaArchive.infrastructure.feign.dto.request.GithubCodeRequest;
import Idea.Archive.IdeaArchive.infrastructure.feign.dto.response.GithubEmailResponse;
import Idea.Archive.IdeaArchive.infrastructure.feign.dto.response.GithubNameResponse;
import Idea.Archive.IdeaArchive.infrastructure.feign.dto.response.GithubTokenResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@RequiredArgsConstructor
@Slf4j
public class GithubAuthService {

    private final MemberRepository memberRepository;
    private final GithubAuth githubAuth;
    private final GithubNameInfo githubNameInfo;
    private final GithubEmailInfo githubEmailInfo;
    private final GithubAuthProperties githubAuthProperties;
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

        log.error("asdfasdf");
        System.out.println(code);
        System.out.println(githubAuthProperties.getBaseUrl());
        System.out.println(githubAuthProperties.getClientSecret());
        System.out.println(githubAuthProperties.getClientId());
        System.out.println(githubAuthProperties.getRedirectUrl());


        GithubTokenResponse githubTokenResponse = githubAuth.githubAuth(
                GithubCodeRequest.builder()
                        .code(code)
                        .clientId(githubAuthProperties.getClientId())
                        .clientSecret(githubAuthProperties.getClientSecret())
                        .redirectUri(githubAuthProperties.getRedirectUrl())
                        .build()
        );

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + githubTokenResponse.getAccessToken());

        System.out.println(githubTokenResponse.getAccessToken());

        GithubNameResponse githubNameResponse = githubNameInfo.githubNameInfo(githubTokenResponse.getAccessToken());
//          GithubEmailResponse githubEmailResponse = githubEmailInfo.githubEmailInfo(githubTokenResponse.getAccessToken());
        GithubEmailResponse githubEmailResponse = githubEmailInfo.githubEmailInfo(headers);

        String email = githubEmailResponse.getEmail();
        String name = githubNameResponse.getName();

        System.out.println(email);
        System.out.println(name);

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
