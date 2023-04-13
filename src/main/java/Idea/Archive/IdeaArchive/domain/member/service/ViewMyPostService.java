package Idea.Archive.IdeaArchive.domain.member.service;


import Idea.Archive.IdeaArchive.domain.member.entity.Member;
import Idea.Archive.IdeaArchive.domain.member.exception.MemberNotFoundException;
import Idea.Archive.IdeaArchive.domain.member.presentation.dto.response.MyPostResponse;
import Idea.Archive.IdeaArchive.domain.post.entity.Post;
import Idea.Archive.IdeaArchive.domain.post.exception.NotExistPostException;
import Idea.Archive.IdeaArchive.domain.post.repository.PostRepository;
import Idea.Archive.IdeaArchive.global.util.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ViewMyPostService {

    private final PostRepository postRepository;
    private final MemberUtil memberUtil;

    @Transactional(readOnly = true)
    public List<MyPostResponse> execute() {
        Member member = memberUtil.currentMember();
        List<Post> posts = postRepository.findByMember(member);
        if(!postRepository.isAllMembersEqual(member,posts)) {
            throw new MemberNotFoundException("존재하지 않은 회원입니다");
        }
        List<MyPostResponse> myPostResponses = MyPostResponse.convertToPostList(posts);
        if (myPostResponses.isEmpty()) {
            throw new NotExistPostException("게시글이 존재하지 않습니다.");
        }
        return myPostResponses;
    }

}
