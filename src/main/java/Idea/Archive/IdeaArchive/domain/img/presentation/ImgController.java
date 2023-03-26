package Idea.Archive.IdeaArchive.domain.img.presentation;

import Idea.Archive.IdeaArchive.domain.img.service.UploadProfileImg;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/img")
@RestController
@RequiredArgsConstructor
public class ImgController {

    private final UploadProfileImg uploadProfileImg;

    @PostMapping("/profile")
    public ResponseEntity<Void> uploadProfileImg(List<MultipartFile> multipartFiles) {
        uploadProfileImg.execute(multipartFiles);
        return ResponseEntity.noContent().build();
    }
}
