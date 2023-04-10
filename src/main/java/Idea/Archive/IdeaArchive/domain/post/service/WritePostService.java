package Idea.Archive.IdeaArchive.domain.post.service;

import Idea.Archive.IdeaArchive.domain.member.entity.Member;
import Idea.Archive.IdeaArchive.domain.post.category.Category;
import Idea.Archive.IdeaArchive.domain.post.entity.Post;
import Idea.Archive.IdeaArchive.domain.post.exception.ManyCategoryException;
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
        if (writePostRequest.getCategory().size()>6){
            throw new ManyCategoryException("카테고리는 최대 5개까지 지정할 수 있습니다");
        }
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
