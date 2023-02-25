package Idea.Archieve.IdeaArchieve.domain.member.entity;

import Idea.Archieve.IdeaArchieve.domain.post.entity.Heart;
import Idea.Archieve.IdeaArchieve.domain.post.entity.Post;
import Idea.Archieve.IdeaArchieve.global.filter.provider.AuthProvider;
import Idea.Archieve.IdeaArchieve.global.filter.role.Role;
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
public class Member{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "profile_image_url", nullable = false)
    private String profileImageUrl;


    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "auth_provider", nullable = false)
    @Enumerated(EnumType.STRING)
    private AuthProvider authProvider;

    @OneToMany(mappedBy = "member",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> post;

    @OneToMany(mappedBy = "member",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Heart> hearts;
    public void updatePassword(String password) {
        this.password = password;
    }

}
