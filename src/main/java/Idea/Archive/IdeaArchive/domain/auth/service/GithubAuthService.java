package Idea.Archive.IdeaArchive.domain.auth.service;

import Idea.Archive.IdeaArchive.domain.auth.entity.RefreshToken;
import Idea.Archive.IdeaArchive.domain.auth.presentation.dto.response.MemberLoginResponse;
import Idea.Archive.IdeaArchive.domain.auth.repository.RefreshTokenRepository;
import Idea.Archive.IdeaArchive.domain.member.entity.Member;
import Idea.Archive.IdeaArchive.domain.member.repository.MemberRepository;
import Idea.Archive.IdeaArchive.global.filter.role.Role;
import Idea.Archive.IdeaArchive.global.security.GithubAuthProperties;
import Idea.Archive.IdeaArchive.global.security.jwt.TokenProvider;
import Idea.Archive.IdeaArchive.global.security.jwt.properties.JwtProperties;
import Idea.Archive.IdeaArchive.infrastructure.feign.client.GithubAuth;
import Idea.Archive.IdeaArchive.infrastructure.feign.client.GithubEmailInfo;
import Idea.Archive.IdeaArchive.infrastructure.feign.client.GithubNameInfo;
import Idea.Archive.IdeaArchive.infrastructure.feign.dto.response.GithubEmailResponse;
import Idea.Archive.IdeaArchive.infrastructure.feign.dto.response.GithubNameResponse;
import Idea.Archive.IdeaArchive.infrastructure.feign.dto.response.GithubTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;


@Service
@RequiredArgsConstructor
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
                            .profileImageUrl(null)
                            .build());
        }
    }

    @Transactional
    public MemberLoginResponse execute(String code) {

        GithubTokenResponse githubTokenResponse = githubAuth.githubAuth(
                code,
                githubAuthProperties.getClientId(),
                githubAuthProperties.getClientSecret(),
                githubAuthProperties.getRedirectUrl()
        );

        System.out.println(githubTokenResponse.getAccess_token());

        HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.add("Authorization", "Bearer " + githubTokenResponse.getAccess_token());

        URI url = URI.create("localhost:8080/");

        RequestEntity<String> request = new RequestEntity<>(headers, HttpMethod.GET, url);

        GithubNameResponse githubNameResponse = githubNameInfo.githubNameInfo(headers);
        System.out.println(githubNameResponse.getName());

        GithubEmailResponse githubEmailResponse = githubEmailInfo.githubEmailInfo(githubTokenResponse.getAccess_token());
        System.out.println(githubEmailResponse.getEmail());

        String email = githubEmailResponse.getEmail();
        String name = githubNameResponse.getName();


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
