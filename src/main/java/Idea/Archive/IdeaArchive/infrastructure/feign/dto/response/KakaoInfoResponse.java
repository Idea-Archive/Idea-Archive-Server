package Idea.Archive.IdeaArchive.infrastructure.feign.dto.response;

import lombok.*;

@Getter
@NoArgsConstructor
public class KakaoInfoResponse {

    private Long id;
    private KakaoAccount kakao_account;

    @Setter
    @Getter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class KakaoAccount {
        private String email;
        private Profile profile;
    }

    @Setter
    @Getter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Profile {
        private String nickname;
    }



}
