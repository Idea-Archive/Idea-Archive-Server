package Idea.Archive.IdeaArchive.domain.auth.service;

import Idea.Archive.IdeaArchive.domain.auth.entity.RefreshToken;
import Idea.Archive.IdeaArchive.domain.auth.exception.RefreshTokenNotFoundException;
import Idea.Archive.IdeaArchive.domain.auth.presentation.dto.response.NewTokenResponse;
import Idea.Archive.IdeaArchive.domain.auth.repository.RefreshTokenRepository;
import Idea.Archive.IdeaArchive.global.security.exception.TokenNotValidException;
import Idea.Archive.IdeaArchive.global.security.jwt.TokenProvider;
import Idea.Archive.IdeaArchive.global.security.jwt.properties.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class TokenReissueService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final TokenProvider tokenProvider;
    private final JwtProperties jwtProperties;

    @Transactional(rollbackFor = Exception.class)
    public NewTokenResponse execute(String requestToken) {
        String email = tokenProvider.getUserEmail(requestToken,jwtProperties.getRefreshSecret());
        RefreshToken token = refreshTokenRepository.findById(email)
                .orElseThrow(() -> new RefreshTokenNotFoundException());

        if (!token.getRefreshToken().equals(requestToken)) {
            throw new TokenNotValidException();
        }

        String accessToken = tokenProvider.generatedAccessToken(email);
        String refreshToken = tokenProvider.generatedRefreshToken(email);
        ZonedDateTime expiredAt = tokenProvider.getExpiredAtToken();
        token.exchangeRefreshToken(refreshToken);
        refreshTokenRepository.save(token);

        return NewTokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .expiredAt(expiredAt)
                .build();
    }
}
