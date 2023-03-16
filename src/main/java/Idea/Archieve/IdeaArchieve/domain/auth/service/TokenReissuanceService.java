package Idea.Archieve.IdeaArchieve.domain.auth.service;


import Idea.Archieve.IdeaArchieve.domain.auth.entity.RefreshToken;
import Idea.Archieve.IdeaArchieve.domain.auth.exception.RefreshTokenNotFoundException;
import Idea.Archieve.IdeaArchieve.domain.auth.presentation.dto.response.NewTokenResponse;
import Idea.Archieve.IdeaArchieve.domain.auth.repository.RefreshTokenRepository;
import Idea.Archieve.IdeaArchieve.global.security.exception.TokenNotValidException;
import Idea.Archieve.IdeaArchieve.global.security.jwt.TokenProvider;
import Idea.Archieve.IdeaArchieve.global.security.jwt.properties.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class TokenReissuanceService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final TokenProvider tokenProvider;
    private final JwtProperties jwtProperties;

    @Transactional(rollbackFor = Exception.class)
    public NewTokenResponse execute(String reqToken){
        String email = tokenProvider.getUserEmail(reqToken,jwtProperties.getRefreshSecret());
        RefreshToken token = refreshTokenRepository.findById(email)
                .orElseThrow(()->new RefreshTokenNotFoundException("존재하지 않은 refreshToken입니다."));

        if(!token.getRefreshToken().equals(reqToken)){
            throw new TokenNotValidException("토큰이 유효하지 않습니다.");
        }

        String accessToken = tokenProvider.generatedAccessToken(email);
        String refreshToken = tokenProvider.generatedRefreshToken(email);
        ZonedDateTime expiredAt = tokenProvider.getExpiredAtToken(accessToken, jwtProperties.getAccessSecret());
        token.exchangeRefreshToken(refreshToken);
        refreshTokenRepository.save(token);
        return NewTokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .expiredAt(expiredAt)
                .build();


    }
}