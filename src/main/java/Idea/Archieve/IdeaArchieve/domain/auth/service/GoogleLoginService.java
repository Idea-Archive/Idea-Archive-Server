package Idea.Archieve.IdeaArchieve.domain.auth.service;

import Idea.Archieve.IdeaArchieve.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GoogleLoginService {

    public final String DEFAULT_USER_IMAGE_URL = "https://image.daily-mission.com/%EA%B8%B0%EB%B3%B8%EC%9D%B4%EB%AF%B8%EC%A7%80/User_Defualt_Image_150_150.png";
    public final String DEFAULT_USER_THUMBNAIL_URL = "https://image.daily-mission.com/%EA%B8%B0%EB%B3%B8%EC%9D%B4%EB%AF%B8%EC%A7%80/User_Default_Image_40_40.png";

    private final MemberRepository memberRepository;

    public 
}
