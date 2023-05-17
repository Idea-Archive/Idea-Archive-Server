package Idea.Archive.IdeaArchive.domain.comment.service;

import Idea.Archive.IdeaArchive.domain.comment.entity.Comment;
import Idea.Archive.IdeaArchive.domain.comment.exception.NotExistCommentException;
import Idea.Archive.IdeaArchive.domain.comment.presentation.dto.request.ModifyCommentRequest;
import Idea.Archive.IdeaArchive.domain.comment.repository.CommentRepository;
import Idea.Archive.IdeaArchive.domain.post.exception.NotVerifyMember;
import Idea.Archive.IdeaArchive.global.util.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ModifyCommentService {

    private final CommentRepository commentRepository;
    private final MemberUtil memberUtil;

    @Transactional(rollbackFor = Exception.class)
    public void execute(Long commentId, ModifyCommentRequest request) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotExistCommentException());
        verifyPostWriter(comment);
        comment.update(request.getContent());
        commentRepository.save(comment);
    }

    private void verifyPostWriter(Comment comment) {
        if (!memberUtil.currentMember().equals(comment.getMember())) {
            throw new NotVerifyMember();
        }
    }
}
