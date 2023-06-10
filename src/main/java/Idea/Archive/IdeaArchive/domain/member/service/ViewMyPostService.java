package Idea.Archive.IdeaArchive.domain.member.service;


import Idea.Archive.IdeaArchive.domain.member.entity.Member;
import Idea.Archive.IdeaArchive.domain.post.entity.Post;
import Idea.Archive.IdeaArchive.domain.post.presentation.dto.response.ViewPostResponse;
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

    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<ViewPostResponse> execute() {
        Member member = memberUtil.currentMember();
        List<Post> posts = postRepository.findByMember(member);
        List<ViewPostResponse> myPostList = ViewPostResponse.convertToPostList(posts);
        return myPostList;
    }

}
