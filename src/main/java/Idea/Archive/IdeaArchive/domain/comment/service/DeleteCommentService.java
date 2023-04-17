package Idea.Archive.IdeaArchive.domain.comment.service;

import Idea.Archive.IdeaArchive.domain.comment.entity.Comment;
import Idea.Archive.IdeaArchive.domain.comment.exception.NotExistCommentException;
import Idea.Archive.IdeaArchive.domain.comment.repository.CommentRepository;
import Idea.Archive.IdeaArchive.domain.post.exception.NotVerifyMember;
import Idea.Archive.IdeaArchive.global.util.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteCommentService {

    private final CommentRepository commentRepository;
    private final MemberUtil memberUtil;

    @Transactional(rollbackFor = Exception.class)
    public void execute(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotExistCommentException("존재하지 않는 댓글입니다."));
        verifyPostWriter(comment);
        commentRepository.deleteById(commentId);
    }

    private void verifyPostWriter(Comment comment) {
        if (!memberUtil.currentMember().equals(comment.getMember())) {
            throw new NotVerifyMember("검증되지 않은 회원입니다.");
        }
    }
}
