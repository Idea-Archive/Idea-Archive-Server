package Idea.Archive.IdeaArchive.domain.member.service;


import Idea.Archive.IdeaArchive.domain.member.entity.Member;
import Idea.Archive.IdeaArchive.domain.member.exception.MemberNotFoundException;
import Idea.Archive.IdeaArchive.domain.member.presentation.dto.response.MyPostResponse;
import Idea.Archive.IdeaArchive.domain.post.exception.NotExistPostException;
import Idea.Archive.IdeaArchive.global.util.MemberUtil;
import lombok.RequiredArgsConstructor;
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
        List<MyPostResponse> posts = MyPostResponse.convertToPostList(member.getPost());
        for(int i=0;i<posts.size();i++){
            if(!posts.get(0).getWriter().equals(member.getName())){
                throw new MemberNotFoundException("회원이 존재하지 않습니다");
            }
        }
        if (posts.size()==0){
            throw new NotExistPostException("게시글이 존재하지 않습니다.");
        }
        return posts;
    }

}
