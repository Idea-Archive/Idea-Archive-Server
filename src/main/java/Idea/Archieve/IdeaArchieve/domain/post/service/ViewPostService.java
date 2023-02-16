package Idea.Archieve.IdeaArchieve.domain.post.service;

import Idea.Archieve.IdeaArchieve.domain.post.Entity.Post;
import Idea.Archieve.IdeaArchieve.domain.post.exception.NotExistPostException;
import Idea.Archieve.IdeaArchieve.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ViewPostService {

    private final PostRepository postRepository;

    private void emptyNotice(List<Post> notice) {
        if (notice.isEmpty()) {
            throw new NotExistPostException("존재하지 않는 게시글입니다.");
        }
    }

    public List<Post> execute() {
        List<Post> posts = postRepository.findAll();
        emptyNotice(posts);
        return posts;
    }
}
