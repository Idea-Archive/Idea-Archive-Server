package Idea.Archive.IdeaArchive.domain.post.service;


import Idea.Archive.IdeaArchive.domain.post.entity.Heart;
import Idea.Archive.IdeaArchive.domain.member.entity.Member;
import Idea.Archive.IdeaArchive.domain.post.repository.HeartRepository;
import Idea.Archive.IdeaArchive.domain.post.entity.Post;
import Idea.Archive.IdeaArchive.domain.post.exception.NotExistPostException;
import Idea.Archive.IdeaArchive.domain.post.repository.PostRepository;
import Idea.Archive.IdeaArchive.global.util.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InsertHeartService {

    private final MemberUtil memberUtil;
    private final PostRepository postRepository;
    private final HeartRepository heartRepository;

    @Transactional
    public void execute(Long postId) {
        Member member = memberUtil.currentMember();

        Post post = postRepository.findById(postId)
                .orElseThrow(()->new NotExistPostException("게시글이 존재하지 않습니다"));

        if(heartRepository.existsHeartByMemberAndPost(member,post)) {
            post.updateHeart(post.getHeartCount()-1);
            post.updatePopularValue();
            postRepository.save(post);
            heartRepository.deleteHeartByMemberAndPost(member,post);
        }else {
            Heart heart = Heart.builder()
                    .member(member)
                    .post(post)
                    .build();

            post.updateHeart(post.getHeartCount()+1);
            heartRepository.save(heart);
            postRepository.save(post);
        }

    }
}
