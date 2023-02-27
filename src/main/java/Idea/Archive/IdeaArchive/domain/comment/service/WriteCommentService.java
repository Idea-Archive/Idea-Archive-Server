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
