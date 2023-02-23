package Idea.Archieve.IdeaArchieve.domain.comment.service;

import Idea.Archieve.IdeaArchieve.domain.comment.entity.Comment;
import Idea.Archieve.IdeaArchieve.domain.comment.exception.NotExistCommentException;
import Idea.Archieve.IdeaArchieve.domain.comment.repository.CommentRepository;
import Idea.Archieve.IdeaArchieve.domain.post.exception.NotVerifyMember;
import Idea.Archieve.IdeaArchieve.global.util.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteCommentService {

    private final CommentRepository commentRepository;
    private final MemberUtil memberUtil;

    private void verifyPostWriter(Comment comment) {
        if (!memberUtil.currentMember().equals(comment.getMember())) {
            throw new NotVerifyMember("검증되지 않은 회원입니다.");
        }
    }

    @Transactional
    public void execute(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotExistCommentException("존재하지 않는 댓글입니다."));
        verifyPostWriter(comment);
        commentRepository.deleteById(commentId);
    }
}
