package Idea.Archive.IdeaArchive.domain.auth.service;

import Idea.Archive.IdeaArchive.global.security.AuthProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ViewGoogleInfo {

    private static final String URL = "%s?client_id=%s&redirect_uri=%s&response_type=code"
            + "&scope=https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile";
    private final AuthProperties authProperties;

    public String execute() {
        return String.format(URL,
                authProperties.getBaseUrl(),
                authProperties.getClientId(),
                authProperties.getRedirectUrl());
    }
}
