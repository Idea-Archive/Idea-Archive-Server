package Idea.Archive.IdeaArchive.domain.post.service;

import Idea.Archive.IdeaArchive.domain.post.enums.Category;
import Idea.Archive.IdeaArchive.domain.post.entity.Post;
import Idea.Archive.IdeaArchive.domain.post.exception.NotExistPostException;
import Idea.Archive.IdeaArchive.domain.post.exception.NotVerifyMember;
import Idea.Archive.IdeaArchive.domain.post.presentation.dto.request.ModifyPostRequest;
import Idea.Archive.IdeaArchive.domain.post.repository.PostRepository;
import Idea.Archive.IdeaArchive.global.util.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ModifyPostService {

    private final PostRepository postRepository;
    private final MemberUtil memberUtil;

    @Transactional(rollbackFor = Exception.class)
    public void execute(Long postId, ModifyPostRequest modifyPostRequest) {
        Post post = postRepository.findById(postId)
                        .orElseThrow(() -> new NotExistPostException());
        verifyPostWriter(post);
        List<String> stringList = modifyPostRequest.getCategory();
        List<Category> categoryList = new ArrayList<Category>();
        for (String s : stringList) {
            Category enumValue = Enum.valueOf(Category.class, s);
            categoryList.add(enumValue);
        }
        post.update(modifyPostRequest.getTitle(), modifyPostRequest.getContent(), categoryList);
        postRepository.save(post);
    }

    private void verifyPostWriter(Post post) {
        if (!memberUtil.currentMember().equals(post.getMember())) {
            throw new NotVerifyMember();
        }
    }

}
