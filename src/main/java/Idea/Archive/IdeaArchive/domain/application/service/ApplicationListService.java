package Idea.Archive.IdeaArchive.domain.application.service;

import Idea.Archive.IdeaArchive.domain.application.presentation.dto.response.ApplicationResponse;
import Idea.Archive.IdeaArchive.domain.application.repository.ApplicationRepository;
import Idea.Archive.IdeaArchive.domain.post.entity.Post;
import Idea.Archive.IdeaArchive.domain.post.exception.NotExistPostException;
import Idea.Archive.IdeaArchive.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationListService {

    private final PostRepository postRepository;

    @Transactional
    public List<ApplicationResponse> execute(Long postId){
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotExistPostException("게시글이 존재하지 않습니다"));
        List<ApplicationResponse> applicationResponses = ApplicationResponse.convertToApplicationList(post.getApplications());
        return applicationResponses;
    }
}
