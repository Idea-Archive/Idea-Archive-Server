package Idea.Archive.IdeaArchive.domain.member.presentation.dto;

import Idea.Archive.IdeaArchive.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class ViewMemberResponse {

    private Long memberId;
    private String name;
    private String profileImageUrl;
    public static ViewMemberResponse convertToMember(Member member) {
        return ViewMemberResponse.builder()
                .memberId(member.getMemberId())
                .name(member.getName())
                .profileImageUrl(member.getProfileImageUrl())
                .build();
    }
}
