package Idea.Archieve.IdeaArchieve.global.config.oauth2;

import Idea.Archieve.IdeaArchieve.domain.member.entity.Member;
import Idea.Archieve.IdeaArchieve.domain.member.exception.MemberNotFoundException;
import Idea.Archieve.IdeaArchieve.domain.member.repository.MemberRepository;
import Idea.Archieve.IdeaArchieve.global.config.oauth2.provider.GoogleUserInfo;
import Idea.Archieve.IdeaArchieve.global.config.oauth2.provider.OAuth2UserInfo;
import Idea.Archieve.IdeaArchieve.global.filter.role.Role;
import Idea.Archieve.IdeaArchieve.global.security.auth.MemberDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;

@RequiredArgsConstructor
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    //구글로 받은 userRequest 데이터에 대해 후처리하는 함수
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("getClientRegistration : " + userRequest.getClientRegistration());
        System.out.println("getAccessToken   : " + userRequest.getAccessToken());

        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println("getAttributes : " + super.loadUser(userRequest).getAttributes());

        ///////////////

        OAuth2UserInfo oAuth2Userinfo = null;

        if(userRequest.getClientRegistration().getRegistrationId().equals("google")){
            System.out.println("구글 로그인 요청");
            oAuth2Userinfo = new GoogleUserInfo(oAuth2User.getAttributes());
        }else{
            System.out.println("지원하지 않는 요청 요청");
        }

        String provider = oAuth2Userinfo.getProvider();
        String providerId = oAuth2Userinfo.getProviderId();
        String name = oAuth2Userinfo.getName();
        String password = passwordEncoder.encode(oAuth2Userinfo.getPassword());
        String profileImgUrl = oAuth2Userinfo.getProfileImg();
        String email = oAuth2Userinfo.getEmail();
        String role = "MEMBER";

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberNotFoundException("존재하지 않는 회원입니다."));

        if(member == null){
            member = Member.builder()
                    .name(name)
                    .password(password)
                    .email(email)
                    .profileImageUrl(profileImgUrl)
                    .build();
            memberRepository.save(member);
        }else{
            System.out.println("이미 로그인된 유저입니다.");
        }

        return new MemberDetails(member,oAuth2User.getAttributes());
    }
}
