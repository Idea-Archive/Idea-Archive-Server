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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchPostService {

    private final PostRepository postRepository;

    @Transactional(rollbackFor = Exception.class)
    public List<ViewPostResponse> execute(String keyword, CategoryRequest categoryRequest) {
        List<Post> posts = new ArrayList<Post>();

        if (categoryRequest.getCategory().isEmpty()) {
            posts = postRepository.findByTitleContainingOrContentContaining(keyword, keyword);
        } else {
            List<Category> categoryList = new ArrayList<Category>();
            for (String s : categoryRequest.getCategory()) {
                Category enumValue = Enum.valueOf(Category.class, s);
                categoryList.add(enumValue);
            }
            List<Post> categories = postRepository.findByAllCategories(categoryList, categoryRequest.getCategory().size());
            for (Post post : categories) {
                if (post.getTitle().contains(keyword)) {
                    posts.add(post);
                } else if (post.getContent().contains(keyword)) {
                    posts.add(post);
                }
            }
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