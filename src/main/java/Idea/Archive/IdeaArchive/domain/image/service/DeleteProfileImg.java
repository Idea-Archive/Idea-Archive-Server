package Idea.Archive.IdeaArchive.domain.image.service;

import Idea.Archive.IdeaArchive.domain.image.exception.NotExistImageException;
import Idea.Archive.IdeaArchive.domain.member.entity.Member;
import Idea.Archive.IdeaArchive.global.util.MemberUtil;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteProfileImg {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    private final AmazonS3 amazonS3;
    private final MemberUtil memberUtil;

    @Transactional(rollbackFor = Exception.class)
    public void execute() {
        Member currentMember = memberUtil.currentMember();
        if (currentMember.getProfileImageUrl().isEmpty()) {
            throw new NotExistImageException();
        }
        deleteImage(currentMember.getProfileImageUrl());
    }

    private void deleteImage(String fileName) {
        amazonS3.deleteObject(new DeleteObjectRequest(bucket, fileName));
    }
}
