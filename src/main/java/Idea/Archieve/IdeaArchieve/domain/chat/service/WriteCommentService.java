package Idea.Archieve.IdeaArchieve.domain.chat.service;

import Idea.Archieve.IdeaArchieve.domain.chat.entity.Comment;
import Idea.Archieve.IdeaArchieve.domain.chat.presentation.dto.request.WriteCommentRequest;
import Idea.Archieve.IdeaArchieve.domain.chat.repository.CommentRepository;
import Idea.Archieve.IdeaArchieve.domain.member.Entity.Member;
import Idea.Archieve.IdeaArchieve.global.util.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WriteCommentService {

    private final CommentRepository commentRepository;
    private final MemberUtil memberUtil;

    public void execute(Long postId, WriteCommentRequest request) {
        Member currentMember = memberUtil.currentMember();
        Comment comment = Comment.builder()
                .content(request.getContent())
                .member(currentMember)
                .build();
    }

}
