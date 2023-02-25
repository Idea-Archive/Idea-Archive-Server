package Idea.Archieve.IdeaArchieve.global.config.oauth2.provider;

public interface OAuth2UserInfo {
    String getProviderId();
    String getProvider();
    String getEmail();
    String getPassword();
    String getProfileImg();
    String getName();
}