package Idea.Archieve.IdeaArchieve.global.security.auth;

import Idea.Archieve.IdeaArchieve.domain.member.Entity.Member;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Getter
public class MemberDetails extends User {

    private final Member member;

    public MemberDetails(Member member) {
        super(member.getEmail(), member.getPassword(), List.of(new SimpleGrantedAuthority(String.valueOf(member.getRole()))));
        this.member = member;
    }
}
