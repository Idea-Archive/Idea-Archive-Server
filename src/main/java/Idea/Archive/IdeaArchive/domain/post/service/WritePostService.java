package Idea.Archive.IdeaArchive.domain.post.service;

import Idea.Archive.IdeaArchive.domain.member.entity.Member;
import Idea.Archive.IdeaArchive.domain.post.entity.Category;
import Idea.Archive.IdeaArchive.domain.post.entity.Post;
import Idea.Archive.IdeaArchive.domain.post.presentation.dto.request.WritePostRequest;
import Idea.Archive.IdeaArchive.domain.post.repository.PostRepository;
import Idea.Archive.IdeaArchive.global.util.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WritePostService {
    private final PostRepository postRepository;
    private final MemberUtil memberUtil;
    @Transactional
    public void execute(WritePostRequest writePostRequest) {
        Member currentMember = memberUtil.currentMember();
        List<String> stringList = writePostRequest.getCategory();
        List<Category> categoryList = new ArrayList<Category>();
        for (String s : stringList) {
            Category enumValue = Enum.valueOf(Category.class, s);
            categoryList.add(enumValue);
        }
        Post post = Post.builder()
                .title(writePostRequest.getTitle())
                .content(writePostRequest.getContent())
                .category(categoryList)
                .member(currentMember)
                .heartCount(0)
                .commentCount(0)
                .applicantCount(0)
                .views(0)
                .build();
        postRepository.save(post);
    }
}
