package Idea.Archive.IdeaArchive.domain.member.entity;

import Idea.Archive.IdeaArchive.domain.post.entity.Heart;
import Idea.Archive.IdeaArchive.domain.post.entity.Post;
import Idea.Archive.IdeaArchive.global.filter.role.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {

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


    @OneToMany(mappedBy = "member",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> post;

    @OneToMany(mappedBy = "member",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Heart> hearts;

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
