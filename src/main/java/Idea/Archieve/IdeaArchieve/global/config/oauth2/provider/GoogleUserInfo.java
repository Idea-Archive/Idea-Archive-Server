package Idea.Archieve.IdeaArchieve.global.config.oauth2.provider;

import java.util.Map;

public class GoogleUserInfo implements OAuth2UserInfo {

    private Map<String, Object> attributes;

    public GoogleUserInfo(Map<String, Object> attributes){
        this.attributes = attributes;
    }

    @Override
    public String getProviderId() {
        return attributes.get("sub").toString();
    }

    @Override
    public String getProvider() {
        return "google";
    }

    @Override
    public String getEmail() {
        return attributes.get("email").toString();
    }

    @Override
    public String getPassword() {
        return attributes.get("password").toString();
    }

    @Override
    public String getProfileImg() {
        return attributes.get("profileImgUrl").toString();
    }

    @Override
    public String getName() {
        return attributes.get("name").toString();
    }
}