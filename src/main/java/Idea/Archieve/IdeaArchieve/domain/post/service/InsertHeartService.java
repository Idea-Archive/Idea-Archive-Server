package Idea.Archieve.IdeaArchieve.domain.post.service;


import Idea.Archieve.IdeaArchieve.domain.post.Entity.Heart;
import Idea.Archieve.IdeaArchieve.domain.member.Entity.Member;
import Idea.Archieve.IdeaArchieve.domain.post.exception.AlreadyInsertHeartException;
import Idea.Archieve.IdeaArchieve.domain.post.repository.HeartRepository;
import Idea.Archieve.IdeaArchieve.domain.post.Entity.Post;
import Idea.Archieve.IdeaArchieve.domain.post.exception.NotExistPostException;
import Idea.Archieve.IdeaArchieve.domain.post.repository.PostRepository;
import Idea.Archieve.IdeaArchieve.global.util.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InsertHeartService {

    private final MemberUtil memberUtil;
    private final PostRepository postRepository;
    private final HeartRepository heartRepository;

    public void execute(Long boardId){
        Member member = memberUtil.currentMember();

        Post post = postRepository.findById(boardId)
                .orElseThrow(()->new NotExistPostException("게시글이 존재하지 않습니다"));

        if(heartRepository.existsHeartByMemberAndPost(member,post)){
            throw new AlreadyInsertHeartException("이미 좋아요를 누르셨습니다.");
        }
        Heart heart = Heart.builder()
                .member(member)
                .post(post)
                .build();

        post.update(post.getHeartCount()+1);
        heartRepository.save(heart);
        postRepository.save(post);

    }
}
