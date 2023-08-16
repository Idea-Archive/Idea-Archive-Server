package Idea.Archive.IdeaArchive.domain.post.service;

import Idea.Archive.IdeaArchive.domain.member.presentation.dto.ViewMemberResponse;
import Idea.Archive.IdeaArchive.domain.post.enums.Category;
import Idea.Archive.IdeaArchive.domain.post.entity.Post;
import Idea.Archive.IdeaArchive.domain.post.exception.NotExistPostException;
import Idea.Archive.IdeaArchive.domain.post.presentation.dto.request.CategoryRequest;
import Idea.Archive.IdeaArchive.domain.post.presentation.dto.response.ViewPostResponse;
import Idea.Archive.IdeaArchive.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchPostService {

    private final PostRepository postRepository;

    @Transactional(rollbackFor = Exception.class)
    public List<ViewPostResponse> execute(String keyword, CategoryRequest categoryRequest) {
        List<Post> posts;

        if (categoryRequest.getCategory().isEmpty()) {
            posts = postRepository.findByTitleContainingOrContentContaining(keyword, keyword);
        } else {
            List<Category> categoryList = categoryRequest.getCategory().stream()
                    .map(category -> Enum.valueOf(Category.class, category))
                    .collect(Collectors.toList());

            posts = postRepository.findByAllCategories(categoryList, categoryRequest.getCategory().size())
                    .stream()
                    .filter(post -> post.getTitle().contains(keyword) || post.getContent().contains(keyword))
                    .collect(Collectors.toList());
        }

        if (posts.isEmpty()) {
            throw new NotExistPostException();
        }

        return posts.stream()
                .map(p -> ViewPostResponse.builder()
                        .postId(p.getPostId())
                        .title(p.getTitle())
                        .content(p.getContent())
                        .category(p.getCategory())
                        .heartCount(p.getHeartCount())
                        .commentCount(p.getCommentCount())
                        .member(ViewMemberResponse.convertToMember(p.getMember()))
                        .createdDate(p.getCreatedDate())
                        .heart(p.getHeart())
                        .build())
                .collect(Collectors.toList());
    }
}