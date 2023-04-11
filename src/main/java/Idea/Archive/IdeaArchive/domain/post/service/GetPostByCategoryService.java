package Idea.Archive.IdeaArchive.domain.post.service;

import Idea.Archive.IdeaArchive.domain.member.presentation.dto.ViewMemberResponse;
import Idea.Archive.IdeaArchive.domain.post.category.Category;
import Idea.Archive.IdeaArchive.domain.post.entity.Post;
import Idea.Archive.IdeaArchive.domain.post.exception.NotExistPostException;
import Idea.Archive.IdeaArchive.domain.post.presentation.dto.response.ViewPostResponse;
import Idea.Archive.IdeaArchive.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetPostByCategoryService {

    private final PostRepository postRepository;
    private final EntityManager entityManager;

    public List<ViewPostResponse> execute(List<String> category) {
        List<Category> categoryList = new ArrayList<Category>();
        for (String s : category) {
            Category enumValue = Enum.valueOf(Category.class, s);
            categoryList.add(enumValue);
        }
        List<Post> posts = postRepository.findByAllCategories(categoryList,(long)category.size());
        if (posts.isEmpty()) {
            throw new NotExistPostException("게시글이 존재하지 않습니다.");
        }
        return posts.stream()
                .map(n -> ViewPostResponse.builder()
                        .id(n.getPostId())
                        .title(n.getTitle())
                        .content(n.getContent())
                        .category(n.getCategory())
                        .heartCount(n.getHeartCount())
                        .commentCount(n.getCommentCount())
                        .member(ViewMemberResponse.convertToMember(n.getMember()))
                        .build())
                .collect(Collectors.toList());
    }



}
