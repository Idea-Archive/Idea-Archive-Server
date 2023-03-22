package Idea.Archive.IdeaArchive.domain.post.service;

import Idea.Archive.IdeaArchive.domain.member.entity.Member;
import Idea.Archive.IdeaArchive.domain.post.exception.NotExistPostException;
import Idea.Archive.IdeaArchive.domain.post.presentation.dto.response.ViewByHeartListResponse;
import Idea.Archive.IdeaArchive.global.util.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ViewHeartListService {

    private final MemberUtil memberUtil;

    @Transactional
    public List<ViewByHeartListResponse> execute() {
        Member member = memberUtil.currentMember();
        List<ViewByHeartListResponse> heartList = ViewByHeartListResponse.convertToHeartList(member.getHearts());
        if(heartList.size()==0){
            throw new NotExistPostException("게시글이 존재하지 않습니다");
        }
        return heartList;
    }
}
