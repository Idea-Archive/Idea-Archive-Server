package Idea.Archieve.IdeaArchieve.domain.comment.service;

import Idea.Archieve.IdeaArchieve.domain.comment.entity.Comment;
import Idea.Archieve.IdeaArchieve.domain.comment.exception.NotExistCommentException;
import Idea.Archieve.IdeaArchieve.domain.comment.presentation.dto.request.ModifyCommentRequest;
import Idea.Archieve.IdeaArchieve.domain.comment.repository.CommentRepository;
import Idea.Archieve.IdeaArchieve.domain.post.exception.NotVerifyMember;
import Idea.Archieve.IdeaArchieve.global.util.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ModifyCommentService {

    private final CommentRepository commentRepository;
    private final MemberUtil memberUtil;

    private void verifyPostWriter(Comment comment) {
        if (!memberUtil.currentMember().equals(comment.getMember())) {
            throw new NotVerifyMember("검증되지 않은 회원입니다.");
        }
    }

    public void execute(Long commentId, ModifyCommentRequest request) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotExistCommentException("존재하지 않는 댓글입니다."));
        verifyPostWriter(comment);
        comment.update(request.getContent());
        commentRepository.save(comment);
    }
}
