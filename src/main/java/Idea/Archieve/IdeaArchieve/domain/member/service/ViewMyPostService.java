package Idea.Archieve.IdeaArchieve.domain.member.service;


import Idea.Archieve.IdeaArchieve.domain.member.entity.Member;
import Idea.Archieve.IdeaArchieve.domain.member.presentation.dto.response.MyPostResponse;
import Idea.Archieve.IdeaArchieve.domain.post.exception.NotExistPostException;
import Idea.Archieve.IdeaArchieve.global.util.MemberUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ViewMyPostService {

    private final MemberUtil memberUtil;

    @Transactional
    public List<MyPostResponse> execute(){
        Member member = memberUtil.currentMember();
        List<MyPostResponse> posts = MyPostResponse.convertToCommentList(member.getPost());
        if (posts.size()==0){
            throw new NotExistPostException("게시글이 존재하지 않습니다.");
        }
        return posts;
    }
}
