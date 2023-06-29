package Idea.Archive.IdeaArchive.domain.member.entity;

import Idea.Archive.IdeaArchive.global.entity.BaseTimeEntity;
import Idea.Archive.IdeaArchive.domain.member.enums.Role;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "profile_image_url")
    private String profileImageUrl;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @PrePersist
    public void setting() {
        this.role = this.role == null ? Role.MEMBER : this.role;
    }

    public Member updatePassword(String password) {
        this.password = password;
        return this;
    }

    public void updateName(String name){
        this.name = name;
    }

    public void updateProfileImg(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }
}
