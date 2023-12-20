package com.korea.project2_team4.Service;

import com.korea.project2_team4.Model.Entity.Image;
import com.korea.project2_team4.Model.Entity.Post;
import com.korea.project2_team4.Repository.ImageRepository;
import lombok.Builder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@Builder
public class ImageService {
    private final ImageRepository imageRepository;

    private String generateRandomFileName(String originalFileName) {
        // 확장자를 포함한 원본 파일 이름에서 확장자를 분리합니다.
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));

        // UUID를 이용하여 랜덤한 파일 이름을 생성합니다.
        String randomFileName = UUID.randomUUID().toString();

        // 확장자를 다시 추가하여 최종 파일 이름을 생성합니다.
        return randomFileName + fileExtension;
    }

    public void uploadPostImage(List<MultipartFile> multipartFiles, Post post) throws IOException, NoSuchAlgorithmException {
        for (MultipartFile multipartFile : multipartFiles) {
            String fileName = multipartFile.getOriginalFilename();
            assert fileName != null;


            String saveName = generateRandomFileName(fileName);

            String savePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

            if (!new File(savePath).exists()) {
                try {
                    new File(savePath).mkdir();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            String filePath = savePath + "\\" + saveName;

            File origFile = new File(filePath);
            multipartFile.transferTo(origFile);

            createPostImage(fileName, saveName, filePath, post);

        }
    }
    public void createPostImage(String fileName, String saveName, String filePath, Post post) {
        Image image = new Image();

        image.setFileName(fileName);

        image.setSaveName(saveName);

        image.setFilePath(filePath);

        image.setPostImages(post);

        this.imageRepository.save(image);

    }


}
