package Idea.Archive.IdeaArchive.domain.post.service;

import Idea.Archive.IdeaArchive.domain.member.entity.Member;
import Idea.Archive.IdeaArchive.domain.post.enums.Category;
import Idea.Archive.IdeaArchive.domain.post.entity.Post;
import Idea.Archive.IdeaArchive.domain.post.presentation.dto.request.WritePostRequest;
import Idea.Archive.IdeaArchive.domain.post.repository.PostRepository;
import Idea.Archive.IdeaArchive.global.util.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WritePostService {

    private final PostRepository postRepository;
    private final MemberUtil memberUtil;

    @Transactional(rollbackFor = Exception.class)
    public void execute(WritePostRequest writePostRequest) {
        Member currentMember = memberUtil.currentMember();
        List<Category> categoryList = writePostRequest.getCategory().stream()
                .map(s -> Enum.valueOf(Category.class, s))
                .collect(Collectors.toList());

        Post post = Post.builder()
                .title(writePostRequest.getTitle())
                .content(writePostRequest.getContent())
                .category(categoryList)
                .member(currentMember)
                .heartCount(0)
                .commentCount(0)
                .applicantCount(0)
                .views(0)
                .heart(false)
                .build();

        postRepository.save(post);
    }
}
