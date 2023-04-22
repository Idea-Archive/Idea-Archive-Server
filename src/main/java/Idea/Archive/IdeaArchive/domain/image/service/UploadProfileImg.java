package Idea.Archive.IdeaArchive.domain.image.service;

import Idea.Archive.IdeaArchive.domain.member.entity.Member;
import Idea.Archive.IdeaArchive.domain.member.exception.MisMatchExtensionException;
import Idea.Archive.IdeaArchive.global.util.MemberUtil;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UploadProfileImg {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    private final AmazonS3 amazonS3;
    private final MemberUtil memberUtil;

    @Transactional(rollbackFor = Exception.class)
    public List<String> execute(List<MultipartFile> multipartFiles) {
        Member currentMember = memberUtil.currentMember();

        List<String> urls = new ArrayList<>();
        multipartFiles.forEach(file -> {
            String fileName = createFileName(file.getOriginalFilename());
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(file.getSize());
            objectMetadata.setContentType(file.getContentType());

            try (InputStream inputStream = file.getInputStream()) {
                amazonS3.putObject(new PutObjectRequest(bucket, fileName, inputStream, objectMetadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead));
            } catch (IOException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "이미지 업로드에 실패했습니다.");
            }

            currentMember.updateProfileImg(fileName);
            urls.add(amazonS3.getUrl(bucket, fileName).toString());
        });
        return urls;
    }

    private String createFileName(String fileName) {
        return UUID.randomUUID().toString().concat(getFileExtension(fileName));
    }

    private String getFileExtension(String fileName) {
        List<String> extensions = Arrays.asList(".jpg", ".JPG", ".jpeg", ".JPEG", ".png", ".PNG");
        String fileExtension = fileName.substring(fileName.lastIndexOf("."));

        try {
            if (!fileName.contains(fileExtension) || !extensions.contains(fileExtension)) {
                throw new MisMatchExtensionException("일치하지 않는 확장자입니다.");
            }
            return fileExtension;
        } catch (StringIndexOutOfBoundsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 형식의 파일(" + fileName + ") 입니다.");
        }
    }
}
