package Idea.Archive.IdeaArchive.domain.member.service;
import Idea.Archive.IdeaArchive.domain.heart.entity.Heart;
import Idea.Archive.IdeaArchive.domain.heart.repository.HeartRepository;
import Idea.Archive.IdeaArchive.domain.member.entity.Member;
import Idea.Archive.IdeaArchive.domain.post.presentation.dto.response.ViewPostResponse;
import Idea.Archive.IdeaArchive.global.util.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ViewMyHeartService {
    private final HeartRepository heartRepository;
    private final MemberUtil memberUtil;

    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<ViewPostResponse> execute() {
        Member member = memberUtil.currentMember();
        List<Heart> heartList = heartRepository.findByMember(member);
        List<ViewPostResponse> myHeartList = ViewPostResponse.convertToHeartList(heartList);
        return myHeartList;
    }
}
