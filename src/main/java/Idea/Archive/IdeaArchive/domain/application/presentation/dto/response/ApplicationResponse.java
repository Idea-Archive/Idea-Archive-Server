package Idea.Archive.IdeaArchive.domain.application.presentation.dto.response;


import Idea.Archive.IdeaArchive.domain.application.entity.Application;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationResponse {

    private Long memberId;
    private String name;
    public static ApplicationResponse convertToApplication(Application application) {
        return ApplicationResponse.builder()
                .memberId(application.getMember().getMemberId())
                .name(application.getMember().getName())
                .build();
    }

    public static List<ApplicationResponse> convertToApplicationList(List<Application> applications) {
        Stream<Application> stream = applications.stream();
        return stream.map(ApplicationResponse::convertToApplication)
                .collect(Collectors.toList());
    }
}
