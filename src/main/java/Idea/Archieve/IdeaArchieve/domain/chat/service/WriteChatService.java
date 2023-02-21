package Idea.Archieve.IdeaArchieve.domain.chat.service;

import Idea.Archieve.IdeaArchieve.domain.chat.entity.Chat;
import Idea.Archieve.IdeaArchieve.domain.chat.presentation.dto.request.WriteChatRequest;
import Idea.Archieve.IdeaArchieve.domain.chat.repository.ChatRepository;
import Idea.Archieve.IdeaArchieve.domain.member.Entity.Member;
import Idea.Archieve.IdeaArchieve.global.util.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WriteChatService {

    private final ChatRepository chatRepository;
    private final MemberUtil memberUtil;

    public void execute(Long postId, WriteChatRequest request) {
        Member currentMember = memberUtil.currentMember();
        Chat chat = Chat.builder()
                .content(request.getContent())
                .member(currentMember)
                .build();
    }

}
