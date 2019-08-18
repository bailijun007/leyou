package com.leyou.upload.service;

import com.leyou.LyUploadApplication;
import com.leyou.common.enums.ExceptionEnums;
import com.leyou.common.exception.LyException;
import com.leyou.upload.config.UploadProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@EnableConfigurationProperties(UploadProperties.class)
public class UploadService {
//private static  final List<String> ALLOW_TYPES= Arrays.asList("image/jpeg","image/png");

    @Autowired
    private UploadProperties prop;


    public String uploadImage(MultipartFile file) {
        try {
            //校验文件类型
            String type = file.getContentType();
            if (!prop.getAllowTypes().contains(type)){
                throw new LyException(ExceptionEnums.FILE_TYPE_NOT_MATCH);
            }
            //校验文件内容
            BufferedImage image = ImageIO.read(file.getInputStream());
            if (null==image){
                throw new LyException(ExceptionEnums.FILE_TYPE_NOT_MATCH);
            }

            //准备目标路径
            File dest=new File(prop.getSaveImagePath(),file.getOriginalFilename());
            //保存文件到本地
            file.transferTo(dest);

            //返回路径
            return prop.getBaseUrl()+file.getOriginalFilename();
        } catch (IOException e) {
           //上传文件失败
            log.error("文件上传失败",e);
            throw new LyException(ExceptionEnums.UPLOAD_FILE_EORRR);
        }

    }
}
