package Idea.Archive.IdeaArchive.domain.comment.service;

import Idea.Archive.IdeaArchive.domain.comment.entity.Comment;
import Idea.Archive.IdeaArchive.domain.comment.presentation.dto.request.WriteCommentRequest;
import Idea.Archive.IdeaArchive.domain.comment.repository.CommentRepository;
import Idea.Archive.IdeaArchive.domain.member.entity.Member;
import Idea.Archive.IdeaArchive.domain.post.entity.Post;
import Idea.Archive.IdeaArchive.domain.post.exception.NotExistPostException;
import Idea.Archive.IdeaArchive.domain.post.repository.PostRepository;
import Idea.Archive.IdeaArchive.global.util.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class WriteCommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final MemberUtil memberUtil;

    @Transactional(rollbackOn = Exception.class)
    public void execute(Long postId, WriteCommentRequest request) {
        Member currentMember = memberUtil.currentMember();
        Post findPost = postRepository.findById(postId)
                .orElseThrow(() -> new NotExistPostException());
        findPost.updateComment(findPost.getCommentCount()+1);
        postRepository.save(findPost);
        Comment comment = Comment.builder()
                .content(request.getContent())
                .post(findPost)
                .member(currentMember)
                .build();
        commentRepository.save(comment);
    }

}
