package Idea.Archive.IdeaArchive.domain.image.presentation;

import Idea.Archive.IdeaArchive.domain.image.service.ChangeProfileService;
import Idea.Archive.IdeaArchive.domain.image.service.DeleteProfileImg;
import Idea.Archive.IdeaArchive.domain.image.service.UploadProfileImg;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/img")
@RestController
@RequiredArgsConstructor
public class ImgController {

    private final UploadProfileImg uploadProfileImg;
    private final ChangeProfileService changeProfileService;
    private final DeleteProfileImg deleteProfileImg;

    @PostMapping
    public ResponseEntity<List<String>> uploadProfileImg(List<MultipartFile> multipartFiles) {
        List<String> response = uploadProfileImg.execute(multipartFiles);
        return ResponseEntity.ok(response);
    }

    @PatchMapping
    public ResponseEntity<Void> changeProfileImg(List<MultipartFile> multipartFiles) {
        changeProfileService.execute(multipartFiles);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteProfileImg() {
        deleteProfileImg.execute();
        return ResponseEntity.ok().build();
    }
}
