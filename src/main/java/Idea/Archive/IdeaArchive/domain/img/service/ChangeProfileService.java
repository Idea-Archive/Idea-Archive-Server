package Idea.Archive.IdeaArchive.domain.img.service;

import Idea.Archive.IdeaArchive.domain.img.service.UploadProfileImg;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChangeProfileService {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    private final UploadProfileImg uploadProfileImg;
    private AmazonS3 amazonS3;

    public void execute(List<MultipartFile> multipartFileList, String fileName) {
        deleteImage(fileName);
        uploadProfileImg.execute(multipartFileList);
    }

    private void deleteImage(String fileName) {
        amazonS3.deleteObject(new DeleteObjectRequest(bucket, fileName));
    }
}
