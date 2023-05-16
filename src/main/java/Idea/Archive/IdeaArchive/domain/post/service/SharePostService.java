package Idea.Archive.IdeaArchive.domain.post.service;

import Idea.Archive.IdeaArchive.domain.post.exception.NotExistPostException;
import Idea.Archive.IdeaArchive.domain.post.presentation.dto.response.SharePostResponse;
import Idea.Archive.IdeaArchive.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SharePostService {

    private final PostRepository postRepository;

    @Transactional(rollbackFor = Exception.class)
    public SharePostResponse execute(Long postId) {
        postRepository.findById(postId)
                .orElseThrow(() -> new NotExistPostException());
        return SharePostResponse.builder()
                .postUrl("http://localhost:8080/post/" + postId).
                build();
    }
}
