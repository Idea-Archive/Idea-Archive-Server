package Idea.Archieve.IdeaArchieve.domain.member.presentation.dto;

import Idea.Archieve.IdeaArchieve.domain.member.Entity.Member;
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
    public static ViewMemberResponse convertToMember(Member member) {
        return ViewMemberResponse.builder()
                .memberId(member.getMemberId())
                .name(member.getName())
                .build();
    }
}
