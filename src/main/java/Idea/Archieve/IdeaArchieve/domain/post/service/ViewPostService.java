package Idea.Archieve.IdeaArchieve.domain.post.service;

import Idea.Archieve.IdeaArchieve.domain.post.Entity.Post;
import Idea.Archieve.IdeaArchieve.domain.post.presentation.dto.response.ViewPostResponse;
import Idea.Archieve.IdeaArchieve.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ViewPostService {

    private final PostRepository postRepository;

    public List<ViewPostResponse> execute() {
        List<Post> postList =  postRepository.findAll();
        List<ViewPostResponse> response = postList.stream().map(
                p -> new ViewPostResponse(p.getTitle(), p.getContent(), p.getCategory(), p.getMember().getName()))
                .collect(Collectors.toList());
        return response;
    }
}
