package com.reggie.controller.admin;

import com.reggie.constant.MessageConstant;
import com.reggie.result.R;
import com.reggie.utils.AliOSSUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.UUID;

/**
 * @Author: ChenXW
 * @Date:2024/2/21 00:05
 * @Description:
 **/

@RestController
@RequestMapping("/admin/common")
@Slf4j
public class CommonController {

    @Autowired
    private AliOSSUtil aliOSSUtil;

    /**
     * @description: 文件上传
     * @author: ChenXW
     * @date: 2024/2/21 0:07
     */
    @PostMapping("/upload")
    @ApiOperation("文件上传")
    public R<String> upload(MultipartFile file){
        log.info(file.getName());

        //原始文件名
        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));

        //将文件上传的阿里云
        String fileName = UUID.randomUUID().toString() + extension;
        try {
            String filePath = aliOSSUtil.upload(file.getBytes(), fileName);
            return R.success(filePath);
        } catch (IOException e) {
            log.error("文件上传失败:{}", e.getMessage());
        }

        return R.error(MessageConstant.UPLOAD_FAILED);
    }

}
