package Idea.Archieve.IdeaArchieve.global.security.auth;

import Idea.Archieve.IdeaArchieve.domain.member.entity.Member;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.List;
import java.util.Map;

@Getter
public class MemberDetails extends User implements OAuth2User {

    private final Member member;
    private Map<String, Object> attributes;

    public MemberDetails(Member member) {
        super(member.getEmail(), member.getPassword(), List.of(new SimpleGrantedAuthority(String.valueOf(member.getRole()))));
        this.member = member;
    }

    public MemberDetails(Member member, Map<String, Object> attributes) {
        super(member.getEmail(), member.getPassword(), List.of(new SimpleGrantedAuthority(String.valueOf(member.getRole()))));
        this.member = member;
        this.attributes = attributes;
    }

    @Override
    public String getName() {
        return attributes.get("sub").toString();
    }
}
