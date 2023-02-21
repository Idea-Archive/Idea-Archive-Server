package Idea.Archieve.IdeaArchieve.domain.comment.service;

import Idea.Archieve.IdeaArchieve.domain.comment.entity.Comment;
import Idea.Archieve.IdeaArchieve.domain.comment.presentation.dto.request.WriteCommentRequest;
import Idea.Archieve.IdeaArchieve.domain.comment.repository.CommentRepository;
import Idea.Archieve.IdeaArchieve.domain.member.Entity.Member;
import Idea.Archieve.IdeaArchieve.domain.post.Entity.Post;
import Idea.Archieve.IdeaArchieve.domain.post.exception.NotExistPostException;
import Idea.Archieve.IdeaArchieve.domain.post.repository.PostRepository;
import Idea.Archieve.IdeaArchieve.global.util.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WriteCommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final MemberUtil memberUtil;

    public void execute(Long postId, WriteCommentRequest request) {
        Member currentMember = memberUtil.currentMember();
        Post findPost = postRepository.findById(postId)
                .orElseThrow(() -> new NotExistPostException("존재하지 않는 게시글입니다."));
        Comment comment = Comment.builder()
                .content(request.getContent())
                .post(findPost)
                .member(currentMember)
                .build();
        commentRepository.save(comment);
    }

}
