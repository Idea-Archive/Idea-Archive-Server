package Idea.Archive.IdeaArchive.global.security.auth;

import Idea.Archive.IdeaArchive.domain.member.entity.Member;
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
